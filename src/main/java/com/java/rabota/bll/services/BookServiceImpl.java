package com.java.rabota.bll.services;


import com.java.rabota.bll.models.book_service.CreateBookInput;
import com.java.rabota.bll.repositories.abstractions.BookRepository;
import com.java.rabota.bll.repositories.abstractions.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;


    public List<BookEntity> getBooks() {
        var books = bookRepository.findAll();
        return books;
    }

    public void createBook(CreateBookInput createBook) {
        var bookEntity = new BookEntity();
        bookEntity.setName(createBook.getName());
        bookEntity.setDescription(createBook.getDescription());
        bookEntity.setPrice(createBook.getPrice());
        bookEntity.setCount(createBook.getCount());
        for (var categoryModel :
                createBook.getCategories()
        ) {
            var existCategory = categoryRepository.findById(categoryModel.getId());
            if(!existCategory.isEmpty()){
                bookEntity.getCategories().add(existCategory.get());
            }
        }
        bookRepository.save(bookEntity);
    }
}
