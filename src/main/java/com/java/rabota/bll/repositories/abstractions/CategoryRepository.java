package com.java.rabota.bll.repositories.abstractions;

import com.java.rabota.dal.entities.CategoryEntity;
import com.java.rabota.dal.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
