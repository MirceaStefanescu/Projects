package com.mycompany.documentmanagement.config;

import com.mycompany.documentmanagement.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class MyWebSecurityConfiguration implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl("/home");
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/home", "/Login", "/static/**", "/signUp").permitAll()
                .requestMatchers("/documents/**").hasRole(Role.USER.name())
                .requestMatchers("/users/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/documents/new", "/documents/edit/**").hasRole(Role.USER.name())
                .requestMatchers("/documents/**", "/users/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}