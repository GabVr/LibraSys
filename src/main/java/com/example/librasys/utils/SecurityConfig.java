package com.example.librasys.utils;

import com.example.librasys.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")
                )
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth -> auth


                        .requestMatchers(
                                "/",
                                "/login",
                                "/api/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/static/**"
                        ).permitAll()

                        // Apenas ADMIN
                        .requestMatchers(
                                "/usuarios/**",
                                "/editoras/**",
                                "/autores/**",
                                "/exemplares/**"
                        ).hasRole("ADMIN")


                        .requestMatchers("/emprestimos/meus")
                        .hasRole("USUARIO")


                        .requestMatchers("/emprestimos/**")
                        .hasRole("ADMIN")


                        .requestMatchers("/dashboard")
                        .authenticated()


                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}