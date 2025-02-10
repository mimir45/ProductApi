package com.se.nobsexam.dto;

import com.se.nobsexam.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private String name;

    public CategoryDto(Category from) {
        this.name = from.getName();
    }
}
