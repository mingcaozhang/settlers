package com.example.repositories;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String username);
}