package com.ezgroceries.shoppinglist.config;

/*
    Created by Ruben Bernaert (JD68212) on 23/10/2019
*/

import com.ezgroceries.shoppinglist.security.handler.AuthenticationSuccessRedirectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String URI_LOGIN = "/login";
    private static final String URI_COCKTAILS = "/cocktails";
    private static final String URI_SHOPPING_LISTS = "/shopping-lists";
    private static final String URI_SWAGGER = "/swagger-ui";
    private static final String URI_ACTUATOR = "/actuator";

    private final DataSource dataSource;
    private final AuthenticationSuccessRedirectHandler successHandler;

    @Autowired
    public SecurityConfig(DataSource dataSource, AuthenticationSuccessRedirectHandler successHandler) {
        this.dataSource = dataSource;
        this.successHandler = successHandler;
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(URI_LOGIN).permitAll()
                .antMatchers(URI_COCKTAILS + "*").permitAll() // Test this with curl, because swagger needs authentication
                .antMatchers(URI_SHOPPING_LISTS + "*").authenticated()
                .regexMatchers(URI_SWAGGER + "*").authenticated()
                .antMatchers(URI_ACTUATOR + "*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().successHandler(successHandler)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // ensure the passwords are encoded properly
        return new BCryptPasswordEncoder();
    }

}
