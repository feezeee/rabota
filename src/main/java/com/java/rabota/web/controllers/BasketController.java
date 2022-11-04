package com.java.rabota.web.controllers;

import com.java.rabota.bll.services.abstractions.OrderService;
import com.java.rabota.bll.services.abstractions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasketController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/basket")
    public String getBasket(Model model, @AuthenticationPrincipal User user)
    {
        orderService.getOrder(1);

        var dbUser = userService.getUserByLogin(user.getUsername());
        if(dbUser == null)
        {
            return "error";
        }

        model.addAttribute("dbUser", dbUser.get());
        model.addAttribute("springUser", user);

        return "basket";
    }
}
