package com.se.nobsexam.service.categoryService;

import com.se.nobsexam.dto.CategoryDto;
import com.se.nobsexam.exception.CategoryNotFound;
import com.se.nobsexam.model.Category;
import com.se.nobsexam.repository.CategoryRepository;
import com.se.nobsexam.service.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetCategoryByNameService implements Query<String, CategoryDto> {
    private final CategoryRepository categoryRepository;

    public GetCategoryByNameService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable(cacheNames = "category" )
    public ResponseEntity<CategoryDto> execute(String input) {
        log.info("Executing GetCategoryByName service");
        Optional<Category> categoryOptional = categoryRepository.findById(input);
        if (categoryOptional.isPresent()) {
            log.info("Category found: {}", categoryOptional.get());
            return ResponseEntity.ok(new CategoryDto(categoryOptional.get()));
        }
        throw  new CategoryNotFound();
    }
}
