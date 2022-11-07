package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.book_service.CreateBookInput;
import com.java.rabota.dal.entities.BookEntity;

import java.util.List;

public interface BookService {
    List<BookEntity> getBooks();
    void createBook(CreateBookInput createBook);
}
