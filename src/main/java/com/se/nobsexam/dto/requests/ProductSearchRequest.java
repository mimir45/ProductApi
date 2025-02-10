package com.se.nobsexam.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchRequest {
    private String search;
    private String category;
    private String sortBy;


}
