package com.restapi.webservices.restfulwebservice.jpa;

import com.restapi.webservices.restfulwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findById(long id);
}
