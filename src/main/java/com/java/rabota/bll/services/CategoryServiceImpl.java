package com.java.rabota.bll.services;

import com.java.rabota.bll.models.category_service.CreateCategoryInput;
import com.java.rabota.bll.models.category_service.GetCategoryOutput;
import com.java.rabota.bll.models.category_service.UpdateCategoryInput;
import com.java.rabota.bll.repositories.abstractions.CategoryRepository;
import com.java.rabota.bll.services.abstractions.CategoryService;
import com.java.rabota.dal.entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<GetCategoryOutput> getCategories(){
        var getCategoryList = new ArrayList<GetCategoryOutput>();

        var categories = categoryRepository.findAll();

        for (var category:
             categories) {

            var newCategory = new GetCategoryOutput();
            newCategory.setId(category.getId());
            newCategory.setName(category.getName());
            newCategory.setBookCount(category.getBooks().size());
            getCategoryList.add(newCategory);
        }
        return getCategoryList;
    }

    @Override
    public Optional<GetCategoryOutput> getCategoryById(int id) {
        var categoryEntity = categoryRepository.findById(id);
        if(categoryEntity.isEmpty())
        {
            return Optional.empty();
        }
        var newCategory = new GetCategoryOutput();
        newCategory.setId(categoryEntity.get().getId());
        newCategory.setName(categoryEntity.get().getName());
        newCategory.setBookCount(categoryEntity.get().getBooks().size());
        return Optional.of(newCategory);
    }

    @Override
    public void createCategory(CreateCategoryInput createCategory) {
        var categoryEntity = new CategoryEntity();
        categoryEntity.setName(createCategory.getName());
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void updateCategory(UpdateCategoryInput updateCategory) {
        var categoryEntity = categoryRepository.findById(updateCategory.getId());
        if(categoryEntity.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        categoryEntity.get().setName(updateCategory.getName());
        categoryRepository.save(categoryEntity.get());
    }

    @Override
    public void deleteCategoryById(int id){
        var category = categoryRepository.findById(id);
        if(!category.isEmpty()){
            categoryRepository.delete(category.get());
        }
    }
}
