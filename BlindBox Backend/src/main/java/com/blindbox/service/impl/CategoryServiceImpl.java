package com.blindbox.service.impl;

import com.blindbox.model.Category;
import com.blindbox.repository.CategoryRepository;
import com.blindbox.request.Create.Category.CategoryCreateRequest;
import com.blindbox.request.Update.Category.CategoryUpdateRequest;
import com.blindbox.service.CategoryService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(@NonNull CategoryCreateRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    @NonNull
    public Category updateCategory(@NonNull Integer id, CategoryUpdateRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (request.getCategoryName() != null) existingCategory.setCategoryName(request.getCategoryName());
        if (request.getDescription() != null) existingCategory.setDescription(request.getDescription());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
}
