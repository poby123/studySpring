package com.example.demo.domain.member.service;

import static com.example.demo.global.exception.ErrorCode.MEMBER_NOT_FOUND;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.redis.EmailCode;
import com.example.demo.domain.member.entity.redis.ResetPasswordCode;
import com.example.demo.domain.member.exception.PasswordResetFailException;
import com.example.demo.domain.member.repositoy.MemberRepository;
import com.example.demo.domain.member.repositoy.redis.EmailCodeRedisRepository;
import com.example.demo.domain.member.repositoy.redis.ResetPasswordCodeRedisRepository;
import com.example.demo.global.exception.types.EmailNotConfirmedException;
import com.example.demo.global.exception.types.EntityNotFoundException;
import com.example.demo.global.exception.types.FileConvertFailException;
import com.example.demo.infra.email.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailCodeService {

	private final MemberRepository memberRepository;
	private final EmailCodeRedisRepository emailCodeRedisRepository;
	private final ResetPasswordCodeRedisRepository resetPasswordCodeRedisRepository;
	private final EmailService emailService;

	private String confirmEmailUI;
	private String resetPasswordEmailUI;

	public void sendEmailConfirmationCode(String username, String email) {
		final String code = createConfirmationCode(6);
		final String text = String.format(confirmEmailUI, email, code, email);
		emailService.sendHtmlTextEmail(username + ", Welcome to Instagram.", text, email);

		final EmailCode emailCode = EmailCode.builder()
			.username(username)
			.email(email)
			.code(code)
			.build();
		emailCodeRedisRepository.save(emailCode);
	}

	public boolean checkEmailCode(String username, String email, String code) {
		final Optional<EmailCode> optionalEmailCode = emailCodeRedisRepository.findByUsername(username);

		if (optionalEmailCode.isEmpty()) {
			throw new EmailNotConfirmedException();
		}

		final EmailCode emailCode = optionalEmailCode.get();

		if (!emailCode.getCode().equals(code) || !emailCode.getEmail().equals(email)) {
			return false;
		}

		emailCodeRedisRepository.delete(emailCode);
		return true;
	}

	public String sendResetPasswordCode(String username) {
		final Member member = memberRepository.findByUsername(username)
			.orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

		final String code = createConfirmationCode(30);
		final String email = member.getEmail();
		final String text = String.format(resetPasswordEmailUI, username, username, code, username, username, code,
			email, username);
		emailService.sendHtmlTextEmail(username + ", recover your account's password.", text, email);

		final ResetPasswordCode resetPasswordCode = ResetPasswordCode.builder()
			.username(username)
			.code(code)
			.build();
		resetPasswordCodeRedisRepository.save(resetPasswordCode);

		return email;
	}

	public boolean checkResetPasswordCode(String username, String code) {
		final Optional<ResetPasswordCode> optionalResetPasswordCode = resetPasswordCodeRedisRepository
			.findByUsername(username);

		if (optionalResetPasswordCode.isEmpty()) {
			throw new EmailNotConfirmedException();
		}

		final ResetPasswordCode resetPasswordCode = optionalResetPasswordCode.get();

		return resetPasswordCode.getCode().equals(code);
	}

	public void deleteResetPasswordCode(String username) {
		final ResetPasswordCode resetPasswordCode = resetPasswordCodeRedisRepository.findByUsername(username)
			.orElseThrow(PasswordResetFailException::new);
		resetPasswordCodeRedisRepository.delete(resetPasswordCode);
	}

	public String createConfirmationCode(int length) {
		final StringBuilder key = new StringBuilder();
		final Random random = new Random();
		for (int i = 0; i < length; i++) {
			final boolean isAlphabet = random.nextBoolean();

			if (isAlphabet) {
				key.append((char)((random.nextInt(26)) + 65));
			} else {
				key.append((random.nextInt(10)));
			}
		}
		return key.toString();
	}

	@PostConstruct
	private void loadEmailUI() {
		try {
			ClassPathResource resource = new ClassPathResource("confirmEmailUI.html");
			confirmEmailUI = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

			resource = new ClassPathResource("resetPasswordEmailUI.html");
			resetPasswordEmailUI = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new FileConvertFailException();
		}
	}

}

