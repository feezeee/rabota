package com.java.rabota.bll.services;


import com.java.rabota.bll.repositories.abstractions.BookRepository;
import com.java.rabota.bll.services.abstractions.BookService;
import com.java.rabota.dal.entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getBooks(){
        var books = bookRepository.findAll();
        return books;
    }
}
