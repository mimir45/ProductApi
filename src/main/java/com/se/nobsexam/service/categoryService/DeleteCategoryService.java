package com.se.nobsexam.service.categoryService;

import com.se.nobsexam.exception.CategoryNotFound;
import com.se.nobsexam.model.Category;
import com.se.nobsexam.repository.CategoryRepository;
import com.se.nobsexam.service.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DeleteCategoryService implements Command<String, Void> {
    private final CategoryRepository categoryRepository;

    public DeleteCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @CacheEvict(cacheNames = "categories", allEntries = true)
    public ResponseEntity<Void> execute(String input) {
        Optional<Category> optionalCategory = categoryRepository.findById(input);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            categoryRepository.delete(category);
            return ResponseEntity.noContent().build();
        }
        throw new CategoryNotFound();
    }
}
