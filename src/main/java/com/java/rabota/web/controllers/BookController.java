package com.java.rabota.web.controllers;

import com.java.rabota.bll.services.abstractions.BookService;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.dal.entities.BookEntity;
import com.java.rabota.dal.entities.CategoryEntity;
import com.java.rabota.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/book")
    public String getBooks(Model model, @AuthenticationPrincipal User user)
    {
        var books = bookService.getBooks();
        var dbUser = userService.getUserByLogin(user.getUsername());
        if(dbUser == null)
        {
            return "error";
        }

        books = getMockBooks();
        books.addAll(getMockBooks());
        books.addAll(getMockBooks());


        model.addAttribute("books", books);
        model.addAttribute("dbUser", dbUser.get());
        model.addAttribute("springUser", user);

        return "book";
    }

    private ArrayList<BookEntity> getMockBooks(){
        var categories = new ArrayList<CategoryEntity>();

        var category1 = new CategoryEntity();
        category1.setId(1);
        category1.setName("test category 1");
        categories.add(category1);


        var category2 = new CategoryEntity();
        category2.setId(1);
        category2.setName("test category 2");
        categories.add(category2);
        for (var i = 0; i < 99; i++)
        {
            categories.add(category2);
        }

        var books = new ArrayList<BookEntity>();
        var book1 = new BookEntity();
        book1.setId(1);
        book1.setName("test name 1");
        book1.setCount(1000000);
        book1.setPrice(106.10);
        book1.setDescription("Рот закрой пока я говорю, чтобы не было крика, тише, и посуда летит по полуууууууууу тише тише тише");
        book1.setCategories(categories);
        books.add(book1);
        return books;
    }
}
