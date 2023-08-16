package com.onlineshop.domain;

import lombok.*;

import javax.persistence.*;

@Entity (name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Supplier.class)
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;

    private String productName;
    private String description;
    private Float price;
    private Boolean isDeleted;
}
