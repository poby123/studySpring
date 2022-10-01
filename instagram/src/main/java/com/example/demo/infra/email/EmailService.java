package com.example.demo.infra.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.global.exception.types.CantSendEmailException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

	private final JavaMailSender javaMailSender;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}

	@Async
	public void sendHtmlTextEmail(String subject, String content, String email) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setTo(email);
			messageHelper.setSubject(subject);
			messageHelper.setText(content, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			log.error("Exception during send mail. error : {}", e);
			throw new CantSendEmailException();
		}
	}

}