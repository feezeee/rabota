package com.java.rabota.web.controllers;

import com.java.rabota.bll.models.category_service.CreateCategoryInput;
import com.java.rabota.bll.models.category_service.UpdateCategoryInput;
import com.java.rabota.bll.services.abstractions.CategoryService;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.web.models.request.category_controller.PostCategoryRequest;
import com.java.rabota.web.models.request.category_controller.PutCategoryRequest;
import com.java.rabota.web.models.response.category_controller.GetCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @RequestMapping("/category")
    public String getCategoriesPage(Model model, @AuthenticationPrincipal User user) {
        try {
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser.isEmpty()) {
                var message = "Упс!";
                model.addAttribute("message", message);
                return "error";
            }

            var categories = categoryService.getCategories();
            var getCategories = new ArrayList<GetCategoryResponse>();
            for (var category :
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
        } catch (Exception ex) {
            var message = "Упс!";
            model.addAttribute("message", message);
            return "error";
        }

    }

    @RequestMapping("/add-category")
    public String addCategoryPage(Model model, @AuthenticationPrincipal User user) {
        try {
            return "add-category";
        } catch (Exception ex) {
            var message = "Упс!";
            model.addAttribute("message", message);
            return "error";
        }
    }

    @PostMapping("/add-category")
    public ResponseEntity addCategory(PostCategoryRequest postCategoryRequest) {
        try {
            var createCategory = new CreateCategoryInput();
            createCategory.setName(postCategoryRequest.getName());
            categoryService.createCategory(createCategory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }

    }

    @RequestMapping("/update-category")
    public String updateCategoryPage(Model model, @AuthenticationPrincipal User user) {
        try {
            return "add-category";
        } catch (Exception ex) {
            var message = "Упс!";
            model.addAttribute("message", message);
            return "error";
        }
    }

    @PutMapping("/update-category")
    public ResponseEntity updateCategory(PutCategoryRequest putCategoryRequest) {
        try {
            var existCategory = categoryService.getCategoryById(putCategoryRequest.getId());
            if (existCategory.isEmpty()) {
                return ResponseEntity.status(400).build();
            }

            var updateCategoryNew = new UpdateCategoryInput();
            updateCategoryNew.setId(putCategoryRequest.getId());
            updateCategoryNew.setName(putCategoryRequest.getName());
            categoryService.updateCategory(updateCategoryNew);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/delete-category")
    public ResponseEntity deleteCategory(@RequestParam int id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.status(200).build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }
}
