package com.rinesarusinovci.online_quizzes_vue_back.security;



import com.rinesarusinovci.online_quizzes_vue_back.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()

                        // Protected endpoints
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF as we are using JWT
                .cors(cors -> {})
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessions
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http,
//                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        // requestMatchers cilat url jon public qe nuk duhet auth
//                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
////                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/**").permitAll()
////                        // per te gjitha routes tjera qe nuk i keni specifiku lart duhet auth
////
////                        // request matchers authorization
////                        // niveli i validimit te komplet routes
////                        // hasRole pranon vetem nje rol, hasAnyRole pranon 2 e me shume
////                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
////
////                        // validoni endpoints specifik
////                        // hasAuthority vetem nje permission, hasAnyAuthority 2 e me shume
////                        .requestMatchers(HttpMethod.GET, "/api/v1/management/**")
////                        .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
////                        .requestMatchers(HttpMethod.POST, "/api/v1/management/**")
////                        .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
////                        .requestMatchers(HttpMethod.PUT, "/api/v1/management/**")
////                        .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
////                        .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**")
////                        .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
//
//
//                        //                        .requestMatchers("/api/v1/admin").hasRole(ADMIN.name())
//                        //                        .requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                        //                        .requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//                        //                        .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//                        //                        .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//
//                        //                        .requestMatchers("api/v1/employees").hasRole(ADMIN.name(), EMPLOYEE.name())
//
//                        .anyRequest().authenticated()
//                )
//                .csrf(csrf -> csrf.disable()) // cross-site request forgery
//                // kur perdorim jwt token nuk na duhet session management per ate e vendosim si stateless
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }



//    @Bean
//    public UserDetailsService userDetailsService(UserRepository repository) {
//        AppUserDetailsService appUserDetailsService = new AppUserDetailsService(repository);
//
//        String email = "user@test.com";
//        repository.findByEmail(email)
//                .orElseGet(() -> {
//                    var newUser = User.builder()
//                            .name("User")
//                            .email(email)
//                            .password(bcryptPasswordEncoder().encode("password"))
//                            .build();
//
//                    return repository.save(newUser);
//                });
//
//        return appUserDetailsService;
//    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return new AppUserDetailsService(repository);
    }

}
