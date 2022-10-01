package com.example.demo.domain.member.dto;

import java.time.LocalDateTime;

import com.example.demo.infra.location.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginDevicesDto {

	private String tokenId;
	private Location location;
	private String device;
	private LocalDateTime lastLoginDate;
	private boolean isCurrent;

}
