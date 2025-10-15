package com.HealthyPetsBackend.config;
import com.HealthyPetsBackend.security.JwtAuthFilter;
import org.springframework.context.annotation.*; import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.*;

@Configuration
public class SecurityConfig {
  private final JwtAuthFilter jwt; public SecurityConfig(JwtAuthFilter j){ jwt=j; }
  @Bean public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf->csrf.disable())
      .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth->auth
        .requestMatchers("/api/health","/api/auth/**").permitAll()
        .anyRequest().authenticated())
      .addFilterBefore(jwt, BasicAuthenticationFilter.class)
      .cors(c->{});
    return http.build();
  }

  @Bean CorsConfigurationSource corsConfigurationSource(){
    var cfg=new CorsConfiguration();
    cfg.addAllowedOrigin("http://localhost:5173"); cfg.addAllowedHeader("*"); cfg.addAllowedMethod("*"); cfg.setAllowCredentials(true);
    var src=new UrlBasedCorsConfigurationSource(); src.registerCorsConfiguration("/**", cfg); return src;
  }

  @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration c) throws Exception { return c.getAuthenticationManager(); }
}

