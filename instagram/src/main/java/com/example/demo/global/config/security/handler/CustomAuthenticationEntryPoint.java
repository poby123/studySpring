package com.example.demo.global.config.security.handler;

import static com.example.demo.global.exception.ErrorCode.INVALID_JWT;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.global.result.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		response.setStatus(INVALID_JWT.getStatus());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		try (OutputStream os = response.getOutputStream()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(os, ErrorResponse.of(INVALID_JWT));
			os.flush();

		}
	}

}