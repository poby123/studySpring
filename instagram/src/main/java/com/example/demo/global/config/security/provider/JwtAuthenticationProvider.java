package com.example.demo.global.config.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.global.config.security.token.JwtAuthenticationToken;
import com.example.demo.global.config.security.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider{
    
    private final JwtUtil jwtUtil;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String jwt = (String)authentication.getPrincipal();
		return jwtUtil.getAuthentication(jwt);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return JwtAuthenticationToken.class.isAssignableFrom(aClass);
	}
}
