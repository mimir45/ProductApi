package com.se.nobsexam.repository;

import com.se.nobsexam.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
    @Query("SELECT p  FROM Product p JOIN p.category c " +
            "WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:category IS NULL OR LOWER(c.name) = LOWER(:category)) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price' THEN p.price " +
            "     WHEN :sort = 'name' THEN p.name " +
            "     ELSE p.id END ASC")
    List<Product> searchProduct(
      @Param("search") String search,
      @Param("category") String category,
      @Param("sort") String sort
    );


}
