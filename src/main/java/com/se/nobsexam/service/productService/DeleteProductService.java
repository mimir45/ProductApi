package com.se.nobsexam.service.productService;

import com.se.nobsexam.exception.ProductNotFound;
import com.se.nobsexam.model.Product;
import com.se.nobsexam.repository.ProductRepository;
import com.se.nobsexam.service.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DeleteProductService implements Command<UUID,Void> {
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(cacheNames = "products")
    public ResponseEntity<Void> execute(UUID input) {
        log.info("Deleting product {}", input);
        Optional<Product> productOptional = productRepository.findById(input);
        if (productOptional.isPresent()) {
            productRepository.deleteById(input);
            log.info("Product {} deleted", input);
            return ResponseEntity.noContent().build();
        }
        throw new ProductNotFound();


    }
}
