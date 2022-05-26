package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.service.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodeId = "bcrypt";

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodeId, new BCryptPasswordEncoder(12));
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(encodeId, encoders);
    }

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private final UserDetailsServiceImpl userDetailsService;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests().antMatchers("/", "/signup", "/login", "/css/**").permitAll()
                    .antMatchers("/member/**").authenticated() // 일반사용자 접근 가능
                    .antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // 매니저, 관리자 접근 가능
                    .antMatchers("/admin/**").hasRole("ADMIN"); // 관리자만 접근 가능
            
            http.formLogin().loginPage("/login").defaultSuccessUrl("/");
            http.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/", true);
            http.exceptionHandling().accessDeniedPage("/forbidden");
            http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
            http.userDetailsService(userDetailsService);
        }
    }

}
