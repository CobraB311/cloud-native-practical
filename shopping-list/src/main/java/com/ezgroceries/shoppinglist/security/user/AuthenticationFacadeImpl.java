package com.ezgroceries.shoppinglist.security.user;

/*
    Created by Ruben Bernaert (JD68212) on 23/10/2019
*/

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public String getUserName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        throw new RuntimeException("No authorisation found");
    }

}
