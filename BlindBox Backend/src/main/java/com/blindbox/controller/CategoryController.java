package com.blindbox.controller;

import com.blindbox.model.Category;
import com.blindbox.request.Create.Category.CategoryCreateRequest;
import com.blindbox.request.Update.Category.CategoryUpdateRequest;
import com.blindbox.response.ResponseData;
import com.blindbox.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Management System", description = "Operations pertaining to categories in the Category Management System")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories", description = "Retrieve a list of all available categories")
    @GetMapping
    public ResponseEntity<ResponseData> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ResponseData(200, true, "Categories retrieved successfully", categories, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve categories", null, null));
        }
    }

    @Operation(summary = "Get a category by ID", description = "Retrieve a single category using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getCategoryById(@PathVariable Integer id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ResponseData(200, true, "Category retrieved successfully", category, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Category not found", null, null));
        }
    }

    @Operation(summary = "Create a new category", description = "Add a new category to the catalog")
    @PostMapping
    public ResponseEntity<ResponseData> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        try {
            Category createdCategory = categoryService.createCategory(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Category created successfully", createdCategory, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to create category", null, null));
        }
    }

    @Operation(summary = "Update an existing category", description = "Update an existing category using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateCategory(@PathVariable Integer id, @RequestBody CategoryUpdateRequest request) {
        try {
            Category updatedCategory = categoryService.updateCategory(id, request);
            return ResponseEntity.ok(new ResponseData(200, true, "Category updated successfully", updatedCategory, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update category", null, null));
        }
    }

    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}