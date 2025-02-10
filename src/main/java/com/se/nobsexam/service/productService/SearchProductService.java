package com.se.nobsexam.service.productService;

import com.se.nobsexam.dto.ProductDto;
import com.se.nobsexam.dto.requests.ProductSearchRequest;
import com.se.nobsexam.model.Product;
import com.se.nobsexam.repository.ProductRepository;
import com.se.nobsexam.service.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SearchProductService implements Query<ProductSearchRequest, List<ProductDto>> {
    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDto>> execute(ProductSearchRequest input) {
        if(input.getSortBy() == null || input.getSortBy().isEmpty()) {
            String    sortBy = "id";


        }

        String search = input.getSearch();
        String category = input.getCategory();
        String sortBy = input.getSortBy();

       List<Product> products = productRepository.searchProduct(search,category,sortBy);
       List<ProductDto> productDtos = products.stream().map(ProductDto::new).limit(10).toList();
       return ResponseEntity.ok(productDtos);
    }
}
