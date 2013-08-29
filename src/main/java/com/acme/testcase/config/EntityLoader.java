package com.acme.testcase.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.testcase.model.Authority;
import com.acme.testcase.model.User;
import com.acme.testcase.repository.AuthorityRepository;
import com.acme.testcase.repository.UserRepository;

@Component
public class EntityLoader {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@PostConstruct
	private void load() {

		Authority authority = new Authority("ROLE_USER");

		authorityRepository.save(authority);

		User user = new User();
		user.setUsername("martin");
		user.setPassword("secret");
		user.getAuthorities().add(authority);

		userRepository.save(user);
	}
}
