package br.com.chromatec.springwebschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated() // Secure your API endpoints
                        .anyRequest().authenticated() // Ensure all other requests also require auth
                )
                .httpBasic(withDefaults())
                // IMPORTANT: Disable CSRF for your API
                // This is generally recommended for REST APIs that will use token-based authentication (like OAuth2/JWT)
                // and do not rely on session cookies for authentication.
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    // You might also need to configure a PasswordEncoder bean if you're using plain text passwords
    // in application.properties or storing hashed passwords.
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder(); // Recommended for production
    // }
}
