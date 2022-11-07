package com.java.rabota.bll.services;

import com.java.rabota.bll.models.category_service.GetCategory;
import com.java.rabota.bll.repositories.abstractions.CategoryRepository;
import com.java.rabota.bll.services.abstractions.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<GetCategory> getCategories(){
        var getCategoryList = new ArrayList<GetCategory>();

        var categories = categoryRepository.findAll();

        for (var category:
             categories) {

            var newCategory = new GetCategory();
            newCategory.setId(category.getId());
            newCategory.setName(category.getName());
            newCategory.setBookCount(category.getBooks().size());
            getCategoryList.add(newCategory);
        }
        return getCategoryList;
    }
}
