package com.mehmetyalvacli.microservice.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
@NamedEntityGraph(
        name = "Product.withDetails",
        attributeNodes = {
                @NamedAttributeNode("user")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Double taxRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Ürünü oluşturan kullanıcının ID'si

    // Constructor, Getters, Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
