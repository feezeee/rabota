package com.java.rabota.bll.services;


import com.java.rabota.bll.models.book_service.CategoryModelForGetBookOutput;
import com.java.rabota.bll.models.book_service.CreateBookInput;
import com.java.rabota.bll.models.book_service.GetBookOutput;
import com.java.rabota.bll.models.book_service.UpdateBookInput;
import com.java.rabota.bll.models.category_service.GetCategoryOutput;
import com.java.rabota.bll.repositories.abstractions.BookRepository;
import com.java.rabota.bll.repositories.abstractions.CategoryRepository;
import com.java.rabota.bll.services.abstractions.BookService;
import com.java.rabota.dal.entities.BookEntity;
import com.java.rabota.dal.entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<GetBookOutput> getBooks(){
        var books = new ArrayList<GetBookOutput>();

        var bookEntities = bookRepository.findAll();

        for (var bookEntity:
                bookEntities) {
            var newBook = new GetBookOutput();
            newBook.setId(bookEntity.getId());
            newBook.setName(bookEntity.getName());
            newBook.setDescription(bookEntity.getDescription());
            newBook.setPrice(bookEntity.getPrice());
            newBook.setCount(bookEntity.getCount());

            for (var category: bookEntity.getCategories()) {
                var newCategory = new CategoryModelForGetBookOutput();
                newCategory.setId(category.getId());
                newCategory.setName(category.getName());
                newBook.getCategories().add(newCategory);
            }

            books.add(newBook);
        }
        return books;
    }
    @Override
    public Optional<GetBookOutput> getBookById(int id) {
        var bookEntity = bookRepository.findById(id);
        if(bookEntity.isEmpty())
        {
            return Optional.empty();
        }

        var newBook = new GetBookOutput();
        newBook.setId(bookEntity.get().getId());
        newBook.setName(bookEntity.get().getName());
        newBook.setDescription(bookEntity.get().getDescription());
        newBook.setPrice(bookEntity.get().getPrice());
        newBook.setCount(bookEntity.get().getCount());
        return Optional.of(newBook);
    }
    @Override
    public void createBook(CreateBookInput createBookInput) {
        var createBook = new BookEntity();
        createBook.setName(createBookInput.getName());
        createBook.setDescription(createBookInput.getDescription());
        createBook.setPrice(createBookInput.getPrice());
        createBook.setCount(createBookInput.getCount());

        for (var categoryModel :
                createBook.getCategories()
        ) {
            var existCategory = categoryRepository.findById(categoryModel.getId());
            if(!existCategory.isEmpty()){
                createBook.getCategories().add(existCategory.get());
            }
        }

        bookRepository.save(createBook);
    }
    @Override
    public void updateBook(UpdateBookInput updateBookInput) {
        var bookEntity = bookRepository.findById(updateBookInput.getId());
        if(bookEntity.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        bookEntity.get().setName(updateBookInput.getName());
        bookEntity.get().setDescription(updateBookInput.getDescription());
        bookEntity.get().setPrice(updateBookInput.getPrice());
        bookEntity.get().setCount(updateBookInput.getCount());

        for (var categoryModel :
                updateBookInput.getCategories()
        ) {
            var existCategory = categoryRepository.findById(categoryModel.getId());
            if(!existCategory.isEmpty()){
                bookEntity.get().getCategories().add(existCategory.get());
            }
        }
        bookRepository.save(bookEntity.get());
    }
    @Override
    public void deleteBookById(int id){
        var book = bookRepository.findById(id);
        if(!book.isEmpty()){
            bookRepository.delete(book.get());
        }
    }

}
