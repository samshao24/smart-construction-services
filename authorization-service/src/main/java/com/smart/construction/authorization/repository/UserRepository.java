package com.smart.construction.authorization.repository;

import com.smart.construction.authorization.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
