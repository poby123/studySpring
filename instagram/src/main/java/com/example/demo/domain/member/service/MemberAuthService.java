package com.example.demo.domain.member.service;

import static com.example.demo.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.demo.global.exception.ErrorCode.PASSWORD_ALREADY_EXIST;
import static com.example.demo.global.exception.ErrorCode.USERNAME_ALREADY_EXIST;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.dto.JwtDto;
import com.example.demo.domain.member.dto.RegisterRequestDto;
import com.example.demo.domain.member.dto.ResetPasswordRequest;
import com.example.demo.domain.member.dto.UpdatePasswordRequestDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.AccountMismatchException;
import com.example.demo.domain.member.exception.PasswordResetFailException;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.example.demo.global.config.security.util.AuthUtil;
import com.example.demo.global.config.security.util.JwtUtil;
import com.example.demo.global.exception.types.EntityAlreadyExistException;
import com.example.demo.global.exception.types.EntityNotFoundException;
import com.example.demo.infra.location.Location;
import com.example.demo.infra.location.LocationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberAuthService {
    private final AuthUtil authUtil;
	private final JwtUtil jwtUtil;
	private final EmailCodeService emailCodeService;
	private final MemberRepository memberRepository;
	private final RefreshTokenService refreshTokenService;
	private final LocationService locationService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	// private final SearchMemberRepository searchMemberRepository;
	// private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@Transactional(readOnly = true)
	public boolean checkUsername(String username) {
		return !memberRepository.existsByUsername(username);
	}

	@Transactional
	public boolean register(RegisterRequestDto registerRequest) {
		if (memberRepository.existsByUsername(registerRequest.getUsername())) {
			throw new EntityAlreadyExistException(USERNAME_ALREADY_EXIST);
		}
		final String username = registerRequest.getUsername();

		if (!emailCodeService.checkEmailCode(username, registerRequest.getEmail(), registerRequest.getCode())) {
			return false;
		}

		final String encryptedPassword = bCryptPasswordEncoder.encode(registerRequest.getPassword());
        registerRequest.setPassword(encryptedPassword);
		final Member member = registerRequest.toEntity();
        
		memberRepository.save(member);

		return true;
	}

	public void sendEmailConfirmation(String username, String email) {
		if (memberRepository.existsByUsername(username)) {
			throw new EntityAlreadyExistException(USERNAME_ALREADY_EXIST);
		}
		emailCodeService.sendEmailConfirmationCode(username, email);
	}

	@Transactional
	public void updatePassword(UpdatePasswordRequestDto updatePasswordRequest) {
		final Member member = authUtil.getLoginMember();
		if (!bCryptPasswordEncoder.matches(updatePasswordRequest.getOldPassword(), member.getPassword())) {
			throw new AccountMismatchException();
		}
		if (updatePasswordRequest.getOldPassword().equals(updatePasswordRequest.getNewPassword())) {
			throw new EntityAlreadyExistException(PASSWORD_ALREADY_EXIST);
		}
		final String encryptedPassword = bCryptPasswordEncoder.encode(updatePasswordRequest.getNewPassword());
		member.setEncryptedPassword(encryptedPassword);
		memberRepository.save(member);
	}

	@Transactional
	public String sendResetPasswordCode(String username) {
		return emailCodeService.sendResetPasswordCode(username);
	}

	
	@Transactional
	public JwtDto resetPassword(ResetPasswordRequest resetPasswordRequest, String device, String ip) {
		final Member member = memberRepository.findByUsername(resetPasswordRequest.getUsername())
			.orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

		if (!emailCodeService.checkResetPasswordCode(resetPasswordRequest.getUsername(),
			resetPasswordRequest.getCode())) {
			throw new PasswordResetFailException();
		}
		if (bCryptPasswordEncoder.matches(resetPasswordRequest.getNewPassword(), member.getPassword())) {
			throw new EntityAlreadyExistException(PASSWORD_ALREADY_EXIST);
		}

		final String encryptedPassword = bCryptPasswordEncoder.encode(resetPasswordRequest.getNewPassword());
		member.setEncryptedPassword(encryptedPassword);
		memberRepository.save(member);

		final JwtDto jwtDto = jwtUtil.generateJwtDto(member);
		final Location location = locationService.getLocation(ip);

		refreshTokenService.addRefreshToken(member.getId(), jwtDto.getRefreshToken(), device, location);
		emailCodeService.deleteResetPasswordCode(resetPasswordRequest.getUsername());

		return jwtDto;
	}

	@Transactional
	public boolean checkResetPasswordCode(String username, String code) {
		return emailCodeService.checkResetPasswordCode(username, code);
	}

	@Transactional
	public void logout(String refreshToken) {
		refreshTokenService.deleteRefreshTokenWithValue(authUtil.getLoginMemberId(), refreshToken);
	}

	// public List<LoginDevicesDto> getLoginDevices(String currentToken) {
	// 	final Member member = authUtil.getLoginMember();
	// 	return refreshTokenService.getLoginDevices(member.getId(), currentToken);
	// }

	// @Transactional
	// public void logoutDevice(String tokenId) {
	// 	refreshTokenService.deleteRefreshTokenWithId(authUtil.getLoginMemberId(), tokenId);
	// }
}
