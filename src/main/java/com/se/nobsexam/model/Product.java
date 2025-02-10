package com.se.nobsexam.model;

import com.se.nobsexam.dto.requests.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "category_name", referencedColumnName = "name")
    private Category category;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private Region region;
    public Product(UUID id,ProductRequest productRequest) {
        this.id = id;
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.price = productRequest.getPrice();
        this.manufacturer = productRequest.getManufacturer();
        this.category =new Category(productRequest.getCategory());
        this.region=Region.valueOf(productRequest.getRegion());
    }
    public Product(ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.price = productRequest.getPrice();
        this.manufacturer = productRequest.getManufacturer();
        this.category=new Category(productRequest.getCategory());
        this.region=Region.valueOf(productRequest.getRegion());
    }


}
