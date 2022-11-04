package com.java.rabota.bll.repositories.abstractions;

import com.java.rabota.dal.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
