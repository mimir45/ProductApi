package com.se.nobsexam.service.categoryService;

import com.se.nobsexam.dto.CategoryDto;
import com.se.nobsexam.exception.CategoryAlreadyExists;
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
public class CreateCategoryService implements Command<Category,CategoryDto>{
    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @CacheEvict(cacheNames = "categories",allEntries = true)
    public ResponseEntity<CategoryDto> execute(Category input) {
        Optional<Category>  categoryOptional= categoryRepository.findById(input.getName());
        log.info(categoryOptional.isPresent()?"Category found":"Category not found");
        if (categoryOptional.isEmpty()) {
            Category category =Category.builder()
                    .name(input.getName())
                    .build();
            categoryRepository.save(category);
            log.info(category.toString());
            return ResponseEntity.ok(new CategoryDto(category));
        }
       throw new CategoryAlreadyExists();
    }
}
