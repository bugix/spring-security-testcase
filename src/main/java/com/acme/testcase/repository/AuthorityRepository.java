package com.acme.testcase.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.testcase.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
