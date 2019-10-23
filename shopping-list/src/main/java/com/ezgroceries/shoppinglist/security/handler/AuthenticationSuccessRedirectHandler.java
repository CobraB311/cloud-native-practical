package com.ezgroceries.shoppinglist.security.handler;

/*
    Created by Ruben Bernaert (JD68212) on 23/10/2019
*/

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessRedirectHandler implements AuthenticationSuccessHandler {

    private static final String REDIRECT_SUCCESS = "/swagger-ui.html";

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (authentication.isAuthenticated()) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, REDIRECT_SUCCESS);
        }
    }

}
