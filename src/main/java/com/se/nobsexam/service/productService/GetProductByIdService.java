package com.se.nobsexam.service.productService;

import com.se.nobsexam.dto.ProductDto;
import com.se.nobsexam.exception.ProductNotFound;
import com.se.nobsexam.model.Product;
import com.se.nobsexam.repository.ProductRepository;
import com.se.nobsexam.service.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class GetProductByIdService implements Query<UUID, ProductDto> {
    private final ProductRepository productRepository;

    public GetProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDto> execute(UUID input) {
        log.info("Get product by id: {}", input);
        Optional<Product> product = productRepository.findById(input);
        if (product.isPresent()) {
            ProductDto productDto = new ProductDto(product.get());
            return ResponseEntity.ok().body(productDto);
        }
        log.info("Product not found: {}", input);
        throw  new ProductNotFound();
    }
}
