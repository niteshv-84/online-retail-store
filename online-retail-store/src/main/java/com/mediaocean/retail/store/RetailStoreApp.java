package com.mediaocean.retail.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Spring boot starter class for the Retail Store application
 *
 */
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
public class RetailStoreApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RetailStoreApp.class, args);
    }
}
