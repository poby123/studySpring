package com.example.demo.global.config.security.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.domain.member.dto.JwtDto;
import com.example.demo.domain.member.dto.JwtResponseDto;
import com.example.demo.domain.member.service.RefreshTokenService;
import com.example.demo.global.config.security.util.JwtUtil;
import com.example.demo.global.config.security.util.RequestExtractor;
import com.example.demo.global.result.ResultCode;
import com.example.demo.global.result.ResultResponse;
import com.example.demo.infra.location.Location;
import com.example.demo.infra.location.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtUtil jwtUtil;
	private final LocationService locationService;
	private final RefreshTokenService refreshTokenService;
	private final int REFRESH_TOKEN_EXPIRES = 60 * 60 * 24 * 7; // 7일
	private final ResultCode DEFAULT_RESULT_CODE = ResultCode.LOGIN_SUCCESS;
	private Map<String, ResultCode> resultCodeMap;
	
	@Value("${auth.cookie-domain}")
	private String COOKIE_DOMAIN;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authentication) throws IOException, ServletException {
		this.onAuthenticationSuccess(request, response, authentication);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		final JwtDto jwtDto = jwtUtil.generateJwtDto(authentication);
		final Location location = locationService.getLocation(RequestExtractor.getClientIP(request));
		
		refreshTokenService.addRefreshToken(Long.valueOf(authentication.getName()), jwtDto.getRefreshToken(),
			RequestExtractor.getDevice(request), location);

		final JwtResponseDto jwtResponse = JwtResponseDto.builder()
			.type(jwtDto.getType())
			.accessToken(jwtDto.getAccessToken())
			.build();

		addCookie(response, jwtDto.getRefreshToken());

		final ResultCode resultCode = getResultCode(request);

		response.setStatus(resultCode.getStatus());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		try (OutputStream os = response.getOutputStream()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(os, ResponseEntity.ok(ResultResponse.of(resultCode, jwtResponse)).getBody());
			os.flush();
		}
	}

	public void setResultCodeMap(Map<String, ResultCode> resultCodeMap) {
		this.resultCodeMap = resultCodeMap;
	}

	protected ResultCode getResultCode(HttpServletRequest request) {
		if (resultCodeMap != null && resultCodeMap.containsKey(request.getRequestURI())) {
			return resultCodeMap.get(request.getRequestURI());
		} else {
			return DEFAULT_RESULT_CODE;
		}
	}

	protected void addCookie(HttpServletResponse response, String refreshTokenString) {
		final Cookie cookie = new Cookie("refreshToken", refreshTokenString);

		cookie.setMaxAge(REFRESH_TOKEN_EXPIRES);

		// cookie.setSecure(true); https 미지원
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setDomain(COOKIE_DOMAIN);

		response.addCookie(cookie);
	}

}
