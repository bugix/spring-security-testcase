package com.acme.testcase.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.testcase.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	User findByUsername(String username);
}
