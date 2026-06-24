package com.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

	
	Optional<Message> findBySecretAndPassword(String secret, String password);
	
	Optional<Message> findBySecret(String secret);
}
