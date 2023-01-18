package com.wiqrug.MyFirstWebApp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

//We will create a number of spring beans and return them back
@Configuration
public class SpringSecurityConfiguration {

    //LDAP or Database
    //In memory configuration
    //InMemoryUserDetailsManager

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){

        UserDetails userDetails1 = createNewUser("likos", "likoslikos2");
        UserDetails userDetails2 = createNewUser("alekos", "alekosalekos2");


        return new InMemoryUserDetailsManager(userDetails1,userDetails2);



    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN").build();
        return userDetails;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Make the h2 databae able to open with spring security
    @Bean
    public SecurityFilterChain  filterChain (HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(
                    auth -> auth.anyRequest().authenticated()
            );
            http.formLogin(withDefaults());

            http.csrf().disable();
            http.headers().frameOptions().disable();
        return http.build();
    }

    //All urls are protected
    // A login form is shown for unathorized request
    // We need to disable CSRF
    // Frames
}
