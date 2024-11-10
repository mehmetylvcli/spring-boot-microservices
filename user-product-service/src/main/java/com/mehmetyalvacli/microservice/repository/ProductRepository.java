package com.mehmetyalvacli.microservice.repository;

import com.mehmetyalvacli.microservice.models.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(value = "Product.withDetails", type = EntityGraph.EntityGraphType.LOAD)
    public List<Product> findProductsByUser_id(Long userId);
}
