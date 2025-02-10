package com.se.nobsexam.service.categoryService;

import com.se.nobsexam.dto.CategoryDto;
import com.se.nobsexam.model.Category;
import com.se.nobsexam.repository.CategoryRepository;
import com.se.nobsexam.service.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetCategoryiesService implements Query<Void, List<CategoryDto>> {
    private final CategoryRepository categoryRepository ;

    public GetCategoryiesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable(value = "categories")
    public ResponseEntity<List<CategoryDto>> execute(Void input) {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map(CategoryDto::new).toList();
        log.info("Getting categories");
        return ResponseEntity.ok(categoryDtoList);
    }
}
