package com.reparo.repository;

import com.reparo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
 User findUserByNumber(long number);
 User findUserById(int id);
}
