package com.controle_laazizi.location_voiture.configuration;

import com.controle_laazizi.location_voiture.enums.UserRole;
import com.controle_laazizi.location_voiture.services.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final JwtAuthentificationFilter jwtAuthentificationFilter;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/admin/car/booking/**").permitAll()
                                .requestMatchers("/api/admin/car/**").permitAll()
                                .requestMatchers("/api/admin/car").permitAll()
                                .requestMatchers("/api/admin/cars").permitAll()
                                .requestMatchers("/api/customer/car/**").permitAll()
                                .requestMatchers("/api/customer/car/book").permitAll()
                                .requestMatchers("/api/customer/car/bookings/**").permitAll()
                                .requestMatchers("/api/admin/car/booking/**").permitAll()
                                .requestMatchers("/api/customer/car").permitAll()
                                .requestMatchers("/api/customer/cars").permitAll()
                                .requestMatchers("/api/admin/dashboard").permitAll()
                                .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
                                .requestMatchers("/api/customer/**").hasAnyAuthority(UserRole.CUSTOMER.name())
                                .anyRequest().authenticated())
                .sessionManagement(manager ->
                        manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
