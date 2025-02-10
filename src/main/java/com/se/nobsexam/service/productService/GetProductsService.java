package com.se.nobsexam.service.productService;

import com.se.nobsexam.dto.ProductDto;
import com.se.nobsexam.model.Product;
import com.se.nobsexam.repository.ProductRepository;
import com.se.nobsexam.service.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetProductsService implements Query<Void , List<ProductDto>>
{
    private final ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(cacheNames = "products")
    public ResponseEntity<List<ProductDto>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        log.info("Products found: {} and returning to dto", products);
        List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();

        return ResponseEntity.ok(productDtos);
    }
}
