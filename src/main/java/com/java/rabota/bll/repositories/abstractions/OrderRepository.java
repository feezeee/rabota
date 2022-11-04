package com.java.rabota.bll.repositories.abstractions;

import com.java.rabota.dal.entities.BookEntity;
import com.java.rabota.dal.entities.OrderEntity;
import com.java.rabota.dal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUserId(int userId);

}
