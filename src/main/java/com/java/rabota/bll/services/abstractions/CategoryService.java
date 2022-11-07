package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.category_service.GetCategory;

import java.util.List;

public interface CategoryService {
    List<GetCategory> getCategories();
}
