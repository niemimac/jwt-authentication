package org.jwt.service;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import org.jwt.security.JwtToken;

@Log4j2
@Path("/")
public class LoginService {
    @Inject
    @Getter
    @Setter
//    private UserService userService = new UserServiceImpl();
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public String login(User userToLogin) throws AuthenticationException {
        User user = userService.findUser(userToLogin.getUsername());
        if (user == null) {
            throw new AuthenticationException("Login error");
        }
        if (user.getPassword().equals(userToLogin.getPassword())) {
            log.debug("Username: {} logged in.", user.getUsername());
            return new JwtToken(user).getToken();
        } else {
            throw new AuthenticationException("Login error");
        }
    }

    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) {
        String authenticationToken = (String) request.getHeader(JwtToken.AUTHORIZATION_HEADER);
        log.debug(authenticationToken);
    }
}
