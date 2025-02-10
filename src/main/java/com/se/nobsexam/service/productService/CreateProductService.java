package com.se.nobsexam.service.productService;

import com.se.nobsexam.dto.ProductDto;
import com.se.nobsexam.dto.requests.ProductRequest;
import com.se.nobsexam.exception.ProductAlreadyExists;
import com.se.nobsexam.exception.ProductNameHasProfanity;
import com.se.nobsexam.model.Product;
import com.se.nobsexam.profanity.ProfanityValidator;
import com.se.nobsexam.repository.ProductRepository;
import com.se.nobsexam.service.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CreateProductService implements Command<ProductRequest, ProductDto> {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CacheEvict(cacheNames = "products")
    public ResponseEntity<ProductDto> execute(ProductRequest input) {
        log.info("Creating product {}", input);
        Optional<Product> productOptional = productRepository.findByName(input.getName());

        if(productOptional.isEmpty()){
            boolean hasProfanity =  ProfanityValidator.hasProfanity(input.getName());
            if(hasProfanity){
                throw new ProductNameHasProfanity();
            }
            Product product = new Product(input);
            productRepository.save(product);
            log.info("Created new product with name {}", input.getName());
            return ResponseEntity.ok(new ProductDto(product));
        }
        log.info("Product with name {} already exists", input.getName());
        throw new ProductAlreadyExists();

    }
}
