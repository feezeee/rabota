package com.java.rabota.bll.repositories.abstractions;

import com.java.rabota.dal.entities.UserEntity;
import com.java.rabota.dal.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    Optional<UserRoleEntity> findByName(String name);
}
