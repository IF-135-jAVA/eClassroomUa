package com.softserve.betterlearningroom.configuration.jwt;

import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.softserve.betterlearningroom.entity.CustomUserDetails;
import com.softserve.betterlearningroom.service.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {
	
	private CustomUserDetailsService userDetailsService;
	private JwtProvider jwtProvider;
	
	public static final String AUTHORIZATION = "Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = getTokenFromRequest((HttpServletRequest) request); 
		if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLogin(token);
            CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
		chain.doFilter(request, response);
		
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
