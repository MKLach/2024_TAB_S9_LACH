package com.mklachl.sopkom.repository;

import org.springframework.data.repository.CrudRepository;

import com.mklachl.sopkom.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
