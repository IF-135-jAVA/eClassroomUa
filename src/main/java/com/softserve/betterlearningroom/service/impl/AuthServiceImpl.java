package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.ConfirmationTokenDAO;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.UserDAO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.ConfirmationToken;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.entity.UserPrincipal;
import com.softserve.betterlearningroom.entity.roles.Roles;
import com.softserve.betterlearningroom.exception.TokenExpiredException;
import com.softserve.betterlearningroom.exception.TokenNotFoundException;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.payload.AuthRequest;
import com.softserve.betterlearningroom.payload.SaveUserRequest;
import com.softserve.betterlearningroom.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private JwtProvider jwtProvider;
    private UserMapper userMapper;
    private UserDAO userDao;
    private ConfirmationTokenDAO tokenDao;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender emailSender;

    private final String baseUrl = "http://localhost:8080/api/auth/api/auth/confirm?code=";

    @Override
    public String login(AuthRequest request) throws UsernameNotFoundException {
        User user = userDao.findByEmail(request.getLogin()).orElseThrow(() -> new BadCredentialsException(
                String.format("User with email - %s, not found", request.getLogin())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                    String.format("Wrong password for user with email - %s", request.getLogin()));
        }
        return jwtProvider.generateToken(user.getEmail(), user.getId(), null);
    }

    @Override
    public String setRole(String userRole) {
        Roles role = null;
        switch (userRole.trim()) {
        case ("student"):
            role = Roles.STUDENT;
            break;
        case ("teacher"):
            role = Roles.TEACHER;
            break;
        }
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Generating token with role: " + role);
        return jwtProvider.generateToken(user.getUsername(), user.getId(), role);
    }

    @Override
    public UserDTO saveUser(SaveUserRequest request) throws UserAlreadyExistsException {
        if (userDao.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    String.format("User with email - %s already exists", request.getEmail()));
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setConfirmed(false);
        User savedUser = userDao.save(user);
        log.info(String.format("Saving user with id: %d", savedUser.getId()));

        ConfirmationToken token = ConfirmationToken.builder().code(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).user(savedUser).build();
        tokenDao.save(token);
        log.info(String.format("Saving token for the user with id: %d", savedUser.getId()));

        sendSimpleMessage(user, token);
        log.info(String.format("Send email for the user with email: %d", savedUser.getEmail()));

        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(SaveUserRequest request, Long id) throws UserAlreadyExistsException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id - %d, not found.", id)));

        if (!user.getEmail().equals(request.getEmail()) && userDao.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    String.format("User with email - %s already exists", request.getEmail()));
        }
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        return userMapper.userToUserDTO(userDao.update(user));
    }

    @Override
    public UserDTO confirmUser(String code) throws TokenExpiredException, TokenNotFoundException {
        ConfirmationToken token = tokenDao.findTokenByCode(code)
                .orElseThrow(() -> new TokenNotFoundException(String.format("Token with code - %s, not found.", code)));
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Sorry, your token has expired.");
        }
        User user = userDao.findById(token.getUser().getId()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with id - %d, not found.", token.getUser().getId())));
        user.setConfirmed(true);
        return userMapper.userToUserDTO(userDao.update(user));
    }

    private void sendSimpleMessage(User user, ConfirmationToken token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@belero.com");
            message.setTo(user.getEmail());
            message.setSubject("Confirm your email");
            message.setText(buildEmail(user.getFirstName() + " " + user.getLastName(), baseUrl + token.getCode()));
            emailSender.send(message);  
        } catch (MailException e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }  
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" + "\n"
                + "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" + "\n"
                + "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "    <tbody><tr>\n" + "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" + "        \n"
                + "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
                + "          <tbody><tr>\n" + "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n"
                + "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                + "                  <tbody><tr>\n" + "                    <td style=\"padding-left:10px\">\n"
                + "                  \n" + "                    </td>\n"
                + "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
                + "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
                + "                    </td>\n" + "                  </tr>\n" + "                </tbody></table>\n"
                + "              </a>\n" + "            </td>\n" + "          </tr>\n" + "        </tbody></table>\n"
                + "        \n" + "      </td>\n" + "    </tr>\n" + "  </tbody></table>\n"
                + "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                + "    <tbody><tr>\n" + "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n"
                + "      <td>\n" + "        \n"
                + "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                + "                  <tbody><tr>\n"
                + "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n"
                + "                  </tr>\n" + "                </tbody></table>\n" + "        \n" + "      </td>\n"
                + "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" + "    </tr>\n"
                + "  </tbody></table>\n" + "\n" + "\n" + "\n"
                + "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                + "    <tbody><tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n" + "    <tr>\n"
                + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
                + "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
                + "        \n"
                + "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
                + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
                + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>"
                + "        \n" + "      </td>\n" + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
                + "    </tr>\n" + "    <tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n"
                + "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" + "\n" + "</div></div>";
    }
}