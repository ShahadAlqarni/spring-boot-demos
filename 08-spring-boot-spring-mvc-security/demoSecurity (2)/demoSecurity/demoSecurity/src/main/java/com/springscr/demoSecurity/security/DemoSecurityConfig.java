package com.springscr.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for JDBC ... No more hardcoded users
    @Bean
    public UserDetailsManager userdetailsmanager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username(How to find users)
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        //define query to retrieve the authorities/roles by username (How to find roles)
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
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

        /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        //adding user, password and roles
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
        UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();
        UserDetails susan = User.builder().username("susan").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
*/
}

