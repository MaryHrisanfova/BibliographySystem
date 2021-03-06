package com.github.MaryHrisanfova.BibliographySystem.config;

import com.github.MaryHrisanfova.BibliographySystem.utilities.CustomBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Mariia_Khrisanfova
 */
@Configuration
@ComponentScan(basePackages = {"com.github.MaryHrisanfova.BibliographySystem"})
public class AppConfig {

    private static final String USERS_TEXT_FILE = "classpath:users.properties";
    private static final String SPRING_SECURITY_REALM_NAME = "RealmName";

    @Value(USERS_TEXT_FILE)
    private Resource users;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        Properties properties = new Properties();
        try {
            properties.load(users.getInputStream());
            return new InMemoryUserDetailsManager(properties);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint(SPRING_SECURITY_REALM_NAME);
    }
}
