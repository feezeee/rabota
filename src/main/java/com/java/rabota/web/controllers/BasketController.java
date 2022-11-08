package com.java.rabota.web.controllers;

import com.java.rabota.bll.services.abstractions.BasketService;
import com.java.rabota.bll.services.abstractions.BookService;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.web.models.request.basket_controller.PostBookBasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@Controller
public class BasketController {

    @Autowired
    private BasketService orderService;

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/basket")
    public String getBasket(Model model, @AuthenticationPrincipal User user)
    {
        var dbUser = userService.getUserByLogin(user.getUsername());
        if(dbUser.isEmpty())
        {
            return "error";
        }
        var basket = orderService.getBasket(dbUser.get().getId());

        model.addAttribute("basket", basket);
        model.addAttribute("dbUser", dbUser.get());
        model.addAttribute("springUser", user);

        return "basket";
    }
    @PostMapping("/add-book-to-basket")
    public ResponseEntity addBookToBasket(@RequestBody PostBookBasketRequest bookBasketRequest, @AuthenticationPrincipal User user){
        try {
            if(user == null)
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            var dbUser = userService.getUserByLogin(user.getUsername());
            if(dbUser.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            orderService.addUpdateBook(dbUser.get().getId(), bookBasketRequest.bookId, bookBasketRequest.count);
            return ResponseEntity.ok().build();
        }
        catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/delete-book-from-basket")
    public ResponseEntity deleteBookFromBasket(@RequestParam(name = "book-id") int bookId, @AuthenticationPrincipal User user){
        try {
            if(user == null)
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            var dbUser = userService.getUserByLogin(user.getUsername());
            if(dbUser.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            var book = bookService.getBookById(bookId);
            if(book.isEmpty())
            {
                return ResponseEntity.status(400).build();
            }

            orderService.deleteBookFromBasket(dbUser.get().getId(), bookId);
            return ResponseEntity.ok().build();
        }
        catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

}
