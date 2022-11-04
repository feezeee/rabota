package com.java.rabota.bll.repositories.abstractions;

import com.java.rabota.dal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
        Optional<UserEntity> findByLogin(String login);
}
