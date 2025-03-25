package com.blindbox.service;

import com.blindbox.model.Category;
import com.blindbox.request.Create.Category.CategoryCreateRequest;
import com.blindbox.request.Update.Category.CategoryUpdateRequest;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);

    @NonNull
    Category createCategory(@NonNull CategoryCreateRequest request);

    Category updateCategory(@NonNull Integer id, @NonNull CategoryUpdateRequest request);

    void deleteCategory(Integer id);
}
