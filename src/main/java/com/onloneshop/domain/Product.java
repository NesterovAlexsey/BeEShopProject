package com.onloneshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Categories.class)
    @JoinColumn(name = "categoryId", nullable = false)
    private Categories category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Suppliers.class)
    @JoinColumn(name = "supplierId", nullable = false)
    private Suppliers supplier;

    private String productName;
    private String description;
    private Float price;
    private Boolean isDeleted;
}
