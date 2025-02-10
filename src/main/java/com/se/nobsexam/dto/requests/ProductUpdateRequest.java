package com.se.nobsexam.dto.requests;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductUpdateRequest {
    private UUID id;
    private ProductRequest productRequest;
    public ProductUpdateRequest(UUID id, ProductRequest productRequest) {
        this.id = id;
        this.productRequest = productRequest;
    }

}
