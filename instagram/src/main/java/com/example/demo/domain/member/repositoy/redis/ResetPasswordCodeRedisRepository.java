package com.example.demo.domain.member.repositoy.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.member.entity.redis.ResetPasswordCode;

public interface ResetPasswordCodeRedisRepository extends CrudRepository<ResetPasswordCode, String> {

	public Optional<ResetPasswordCode> findByUsername(String username);

}
