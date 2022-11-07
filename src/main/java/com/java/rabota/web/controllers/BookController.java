package com.java.rabota.web.controllers;

import com.java.rabota.bll.models.book_service.CategoryModelForCreateBookInput;
import com.java.rabota.bll.models.book_service.CreateBookInput;
import com.java.rabota.bll.services.abstractions.BookService;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.dal.entities.BookEntity;
import com.java.rabota.dal.entities.CategoryEntity;
import com.java.rabota.security.SecurityUser;
import com.java.rabota.web.models.request.book_controller.PostBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/book")
    public String getBooks(Model model, @AuthenticationPrincipal User user) {
        var books = bookService.getBooks();
        var dbUser = userService.getUserByLogin(user.getUsername());
        if (dbUser.isEmpty()) {
            return "error";
        }

        model.addAttribute("books", books);
        model.addAttribute("dbUser", dbUser.get());
        model.addAttribute("springUser", user);

        return "book";
    }

    @RequestMapping("/add-book")
    public String addBook(Model model, @AuthenticationPrincipal User user) {
        return "add-book";
    }
    @RequestMapping("/nav-side")
    public String getNavSide(Model model, @AuthenticationPrincipal User user) {
        var dbUser = userService.getUserByLogin(user.getUsername());
        if (dbUser.isEmpty()) {
            return "error";
        }
        model.addAttribute("dbUser", dbUser.get());
        return "nav-side";
    }

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody PostBookRequest book, @AuthenticationPrincipal User user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            var bookModel = new CreateBookInput();
            bookModel.setName(book.name);
            bookModel.setDescription(book.description);
            bookModel.setPrice(book.price);
            bookModel.setCount(book.count);
            for (var category :
                    book.categories) {
                var categoryModel = new CategoryModelForCreateBookInput();
                categoryModel.setId(category.id);
                bookModel.getCategories().add(categoryModel);
            }
            bookService.createBook(bookModel);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }

    }
}
