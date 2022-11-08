package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.category_service.CreateCategoryInput;
import com.java.rabota.bll.models.category_service.GetCategoryOutput;
import com.java.rabota.bll.models.category_service.UpdateCategoryInput;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<GetCategoryOutput> getCategories();
    Optional<GetCategoryOutput> getCategoryById(int id);
    void createCategory(CreateCategoryInput createCategory);
    void updateCategory(UpdateCategoryInput updateCategory);
    void deleteCategoryById(int id);
}
