package com.softserve.betterlearningroom.configuration.jwt;

import com.softserve.betterlearningroom.entity.roles.Roles;
import lombok.extern.java.Log;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@Log
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(String email, Long id, Roles role) {
        Date now = new Date();
        Date expired = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        log.info("generateToken email = " + email + " id = " + id + " role = " + role);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setNotBefore(now)
                .claim("role", role)
                .claim("id", id)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getLogin(String token) {
        log.info("Getting subject... ");
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        log.info("Getting subject " + claims.getSubject());
        return claims.getSubject();
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public Optional<String> getRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return Optional.ofNullable(claims.get("role", String.class));
    }
    
    public Long getId(String token) {
        log.info("Getting id... ");
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        log.info("Getting id " + claims.get("id", Long.class));
        return claims.get("id", Long.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

}
