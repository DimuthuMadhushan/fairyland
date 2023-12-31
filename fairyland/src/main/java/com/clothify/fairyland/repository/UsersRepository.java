package com.clothify.fairyland.repository;

import com.clothify.fairyland.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {


    Optional<Users> findByUserName(String username);
}
