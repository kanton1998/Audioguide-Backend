package com.Audioguide.controller;

import com.Audioguide.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);

    Optional<User> findByEmail(String email);

    Optional<User> findByAuthToken(String authToken);

}
