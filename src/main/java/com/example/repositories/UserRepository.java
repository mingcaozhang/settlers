package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.models.networkModels.User;
import org.springframework.transaction.annotation.Transactional;


/* Creates a table in the mysql database to hold user information */
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
    public User getByUsername( String username);
}

