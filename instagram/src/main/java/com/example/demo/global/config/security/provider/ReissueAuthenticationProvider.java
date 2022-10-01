package com.example.demo.global.config.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.member.entity.redis.RefreshToken;
import com.example.demo.domain.member.service.RefreshTokenService;
import com.example.demo.global.config.security.token.ReissueAuthenticationToken;
import com.example.demo.global.config.security.util.JwtUtil;
import com.example.demo.global.exception.types.LogoutByAnotherException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReissueAuthenticationProvider implements AuthenticationProvider {

	private final JwtUtil jwtUtil;
	private final RefreshTokenService refreshTokenService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String refreshTokenString = (String)authentication.getPrincipal();

		final Authentication authenticated = jwtUtil.getAuthentication(refreshTokenString);

		final String memberId = (String)authenticated.getName();

		// 사용할 수 있는(저장된) 토큰인지 확인
		final RefreshToken refreshToken = refreshTokenService.findRefreshToken(Long.valueOf(memberId),
			refreshTokenString).orElseThrow(LogoutByAnotherException::new);

		this.deleteRefreshToken(refreshToken);

		return authenticated;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return ReissueAuthenticationToken.class.isAssignableFrom(aClass);
	}

	private void deleteRefreshToken(RefreshToken refreshToken) {
		refreshTokenService.deleteRefreshToken(refreshToken);
	}

}
