package com.se.nobsexam.service.productService;

import com.se.nobsexam.dto.ProductDto;
import com.se.nobsexam.dto.requests.ProductUpdateRequest;
import com.se.nobsexam.exception.ProductNotFound;
import com.se.nobsexam.model.Category;
import com.se.nobsexam.model.Product;
import com.se.nobsexam.model.Region;
import com.se.nobsexam.repository.ProductRepository;
import com.se.nobsexam.service.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@Slf4j
public class UpdateProductsService  implements Command<ProductUpdateRequest, ProductDto> {
    private final ProductRepository productRepository;

    public UpdateProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(cacheNames = "product")
    public ResponseEntity<ProductDto> execute(ProductUpdateRequest input) {
        log.info("Updating product {}", input);
        Optional<Product> optionalProduct = productRepository.findById(input.getId());
        log.info("Found product {}", optionalProduct);
        if (optionalProduct.isPresent()) {
            Product updatedProduct=Product.builder()
                    .id(optionalProduct.get().getId())
                    .name(input.getProductRequest().getName())
                    .description(input.getProductRequest().getDescription())
                    .price(input.getProductRequest().getPrice())
                    .region(Region.valueOf(input.getProductRequest().getRegion()))
                    .manufacturer(input.getProductRequest().getManufacturer())
                    .category(Category.builder().name(input.getProductRequest().getCategory()).build())
                    .build();

            log.info("Updating product: {}", updatedProduct);
            productRepository.save(updatedProduct);
            return ResponseEntity.ok(new ProductDto(updatedProduct));

        }
        log.error("Product not found");
        throw new ProductNotFound();

    }


}
