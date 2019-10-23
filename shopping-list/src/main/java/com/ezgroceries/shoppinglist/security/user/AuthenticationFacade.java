package com.ezgroceries.shoppinglist.security.user;

/*
    Created by Ruben Bernaert (JD68212) on 23/10/2019
*/

import org.springframework.security.access.annotation.Secured;

public interface AuthenticationFacade {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    String getUserName();

}
