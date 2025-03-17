package com.blindbox.controller;

import com.blindbox.model.Category;
import com.blindbox.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Management System", description = "Operations pertaining to categories in the Category Management System")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Lấy tất cả danh mục
    @Operation(summary = "Get all categories", description = "Retrieve a list of all available categories")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Lấy danh mục theo ID
    @Operation(summary = "Get a category by ID", description = "Retrieve a single category using its ID")
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    // Tạo danh mục mới
    @Operation(summary = "Create a new category", description = "Add a new category to the catalog")
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // Cập nhật danh mục
    @Operation(summary = "Update an existing category", description = "Update an existing category using its ID")
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // Xóa danh mục
    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
