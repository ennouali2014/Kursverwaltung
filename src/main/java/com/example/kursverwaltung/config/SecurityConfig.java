package com.example.kursverwaltung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    // authentication
    public UserDetailsService userDetailsService() {
/*        UserDetails admin = User.withUsername("ALi")
                .password(encoder.encode("root"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("Alexander")
                .password(encoder.encode("root"))
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin, user);*/
        return new UserInfoUserDetailsService();

    }

/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/p").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();

    }*/

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http.csrf().disable()
                 .authorizeHttpRequests()
                 .requestMatchers("/p/welcome","/p/newUser").permitAll()
                 .and()
                 .authorizeHttpRequests().requestMatchers("/p/**").authenticated()
                 .and().formLogin()
                 .and().build();
     }

 /*   @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/

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
