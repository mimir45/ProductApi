package com.se.nobsexam.controller;

import com.se.nobsexam.dto.CategoryDto;
import com.se.nobsexam.model.Category;
import com.se.nobsexam.service.categoryService.CreateCategoryService;
import com.se.nobsexam.service.categoryService.DeleteCategoryService;
import com.se.nobsexam.service.categoryService.GetCategoryByNameService;
import com.se.nobsexam.service.categoryService.GetCategoryiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final GetCategoryiesService getCategoryiesService;
    private final GetCategoryByNameService getCategoryByNameService;
    private final DeleteCategoryService deleteCategoryService;
    private final CreateCategoryService createCategoryService;

    public CategoryController(GetCategoryiesService getCategoryiesService, GetCategoryByNameService getCategoryByNameService, DeleteCategoryService deleteCategoryService, CreateCategoryService createCategoryService) {
        this.getCategoryiesService = getCategoryiesService;
        this.getCategoryByNameService = getCategoryByNameService;
        this.deleteCategoryService = deleteCategoryService;
        this.createCategoryService = createCategoryService;
    }


    @GetMapping
    private ResponseEntity<List<CategoryDto>> getCategories() {
        return getCategoryiesService.execute(null);

    }
    @GetMapping("/{id}")
    private ResponseEntity<CategoryDto> getCategory(@PathVariable String id) {
        return getCategoryByNameService.execute(id);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        return deleteCategoryService.execute(id);
    }
    @PostMapping
    private ResponseEntity<CategoryDto> createCategory(@RequestBody Category category) {
        return createCategoryService.execute(category);
    }


}
