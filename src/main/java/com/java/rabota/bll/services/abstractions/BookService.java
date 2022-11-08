package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.book_service.CreateBookInput;
import com.java.rabota.bll.models.book_service.GetBookOutput;
import com.java.rabota.bll.models.book_service.UpdateBookInput;
import com.java.rabota.dal.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<GetBookOutput> getBooks();
    Optional<GetBookOutput> getBookById(int id);
    void createBook(CreateBookInput createBookInput);
    void updateBook(UpdateBookInput updateBookInput);
    void deleteBookById(int id);
}
