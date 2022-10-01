package com.example.demo.domain.member.repositoy.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.member.entity.redis.EmailCode;

public interface EmailCodeRedisRepository extends CrudRepository<EmailCode, String> {

	public Optional<EmailCode> findByUsername(String username);

}
