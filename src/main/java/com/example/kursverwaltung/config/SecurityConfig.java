package com.example.kursverwaltung.config;

import com.example.kursverwaltung.repository.UserInfoRepository;
import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private UserInfoRepository repository;

    @Bean
    // authentication
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home","/static/**","/public/**","/logout", "/", "/person/welcome", "/k1/kurs1", "/person/newperson", "/person/welcome", "/person/saveperson", "/person/personen", "/index", "/user/**", "/user/saveUser", "/user/deleteUser/*").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers( "/person/**", "/k1/kurs1/**").authenticated()
                .and().formLogin()
               // .and().formLogin().loginPage("/log")
                .and().build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
