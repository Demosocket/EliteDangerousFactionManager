package com.demosocket.manager.repository;

import com.demosocket.manager.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
