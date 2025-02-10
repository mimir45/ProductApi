package com.se.nobsexam.controller;

import com.se.nobsexam.dto.ProductDto;
import com.se.nobsexam.dto.requests.ProductRequest;
import com.se.nobsexam.dto.requests.ProductSearchRequest;
import com.se.nobsexam.dto.requests.ProductUpdateRequest;
import com.se.nobsexam.service.productService.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductsService getProductsService;
    private final GetProductByIdService getProductByIdService;
    private final UpdateProductsService updateProductsService;
    private final SearchProductService searchProductService;

    public ProductController(CreateProductService createProductService, DeleteProductService deleteProductService, GetProductsService getProductsService, GetProductByIdService getProductByIdService, UpdateProductsService updateProductsService, SearchProductService searchProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.getProductsService = getProductsService;
        this.getProductByIdService = getProductByIdService;
        this.updateProductsService = updateProductsService;
        this.searchProductService = searchProductService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
      return   getProductsService.execute(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        return getProductByIdService.execute(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestParam(required = false) String query,@RequestParam(required = false) String category,@RequestParam(required = false) String sort) {
        ProductSearchRequest productSearchRequest = new ProductSearchRequest(query,category,sort);
        return searchProductService.execute(productSearchRequest);
    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequest productRequest) {
        return   createProductService.execute(productRequest);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest) {
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(id,productRequest);
        return updateProductsService.execute(productUpdateRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        return deleteProductService.execute(id);
    }





}
