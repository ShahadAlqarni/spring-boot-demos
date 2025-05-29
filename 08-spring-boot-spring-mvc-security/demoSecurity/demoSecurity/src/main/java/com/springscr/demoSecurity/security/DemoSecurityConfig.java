package com.springscr.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        //adding user, password and roles
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
        UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();
        UserDetails susan = User.builder().username("susan").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //any request to this app must be authenticated
        http.authorizeHttpRequests(configurer ->
                        configurer
                                // to get path and put restrict
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/system/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/shownMyLoginPage")
                                //login form should Post data to this URL for processing
                                .loginProcessingUrl("/authenticateTheUser")
                                //allow everyone to see login page. No need to be logged in.
                                .permitAll()
                )
                //to add logout button
                .logout(logout -> logout.permitAll()
                )
                //add access-denied page
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
        return http.build();

    }
}
