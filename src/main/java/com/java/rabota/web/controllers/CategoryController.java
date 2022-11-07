package com.java.rabota.web.controllers;

import com.java.rabota.bll.repositories.abstractions.CategoryRepository;
import com.java.rabota.bll.services.abstractions.CategoryService;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.web.models.response.category_controller.GetCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @RequestMapping("/category")
    public String getCategoriesPage(Model model, @AuthenticationPrincipal User user){
        try{
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser.isEmpty()) {
                var message = "Упс!";
                model.addAttribute("message", message);
                return "error";
            }

            var categories = categoryService.getCategories();
            var getCategories = new ArrayList<GetCategoryResponse>();
            for (var category:
                    categories) {
                var newCategory = new GetCategoryResponse();
                newCategory.setId(category.getId());
                newCategory.setName(category.getName());
                newCategory.setBookCount(category.getBookCount());
                getCategories.add(newCategory);
            }
            model.addAttribute("categories", getCategories);
            model.addAttribute("dbUser", dbUser.get());
            model.addAttribute("springUser", user);
            return "category";
        }
        catch (Exception ex){
            var message = "Упс!";
            model.addAttribute("message", message);
            return "error";
        }

    }
}
