package com.java.rabota.web.controllers;

import com.java.rabota.bll.models.book_service.CategoryModelForCreateBookInput;
import com.java.rabota.bll.models.book_service.CategoryModelForUpdateBookInput;
import com.java.rabota.bll.models.book_service.CreateBookInput;
import com.java.rabota.bll.models.book_service.UpdateBookInput;
import com.java.rabota.bll.services.abstractions.BookService;
import com.java.rabota.bll.services.abstractions.CategoryService;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.dal.entities.BookEntity;
import com.java.rabota.dal.entities.CategoryEntity;
import com.java.rabota.security.SecurityUser;
import com.java.rabota.web.models.request.book_controller.PostBookRequest;
import com.java.rabota.web.models.request.book_controller.PutBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/book")
    public String getBooks(Model model, @AuthenticationPrincipal User user) {
        try{
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser.isEmpty()) {
                return "redirect:login";
            }
            var books = bookService.getBooks();
            model.addAttribute("books", books);
            model.addAttribute("dbUser", dbUser.get());
            model.addAttribute("springUser", user);
            return "book";

        }catch (Exception ex)
        {
            var messages = "Упс!";
            model.addAttribute("messages", messages);
            return "error";
        }
    }

    @RequestMapping("/add-book")
    public String addBook(Model model, @AuthenticationPrincipal User user) {
        try{
            var categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            return "add-book";
        }catch (Exception ex){
            var messages = "Упс!";
            model.addAttribute("messages", messages);
            return "error";
        }

    }

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody PostBookRequest book, @AuthenticationPrincipal User user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            var bookModel = new CreateBookInput();
            bookModel.setName(book.getName());
            bookModel.setDescription(book.getDescription());
            bookModel.setPrice(book.getPrice());
            bookModel.setCount(book.getCount());
            for (var category :
                    book.getCategories()) {
                var categoryModel = new CategoryModelForCreateBookInput();
                categoryModel.setId(category.getId());
                bookModel.getCategories().add(categoryModel);
            }
            bookService.createBook(bookModel);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }

    }

    @RequestMapping("/update-book")
    public String updateBook(@RequestParam int bookId, Model model, @AuthenticationPrincipal User user) {
        try{
            var result = bookService.getBookById(bookId);
            if(result.isEmpty()){
                var messages = "Такой книги не существует!";
                model.addAttribute("messages", messages);
                return "error";
            }
            var book = result.get();
            model.addAttribute("book", book);
            var categories = categoryService.getCategories().stream().filter(t-> !book.getCategories().stream().anyMatch(k->k.getName() == t.getName())).toList();
            model.addAttribute("categories", categories);
            return "update-book";
        }catch (Exception ex){
            var messages = "Упс!";
            model.addAttribute("messages", messages);
            return "error";
        }

    }

    @PutMapping("/update-book")
    public ResponseEntity updateBook(@RequestBody PutBookRequest book, @AuthenticationPrincipal User user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            var bookModel = new UpdateBookInput();
            bookModel.setId(book.getId());
            bookModel.setName(book.getName());
            bookModel.setDescription(book.getDescription());
            bookModel.setPrice(book.getPrice());
            bookModel.setCount(book.getCount());
            for (var category :
                    book.getCategories()) {
                var categoryModel = new CategoryModelForUpdateBookInput();
                categoryModel.setId(category.getId());
                bookModel.getCategories().add(categoryModel);
            }
            bookService.updateBook(bookModel);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }

    }

    @DeleteMapping("/delete-book")
    public ResponseEntity deleteCategory(@RequestParam int id, @AuthenticationPrincipal User user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            bookService.deleteBookById(id);
            return ResponseEntity.status(200).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }
}
