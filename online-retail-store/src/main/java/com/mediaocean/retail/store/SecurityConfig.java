package com.mediaocean.retail.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/products*").permitAll()
                .antMatchers(HttpMethod.POST, "/bills*").permitAll()
                .anyRequest().authenticated().and().formLogin().and().
                csrf().disable();

    }


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

       *//* auth
                .userDetailsService(customUserService);*//*
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        auth.

    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}