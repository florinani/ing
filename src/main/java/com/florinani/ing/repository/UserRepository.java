package com.florinani.ing.repository;

import com.florinani.ing.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);
}
