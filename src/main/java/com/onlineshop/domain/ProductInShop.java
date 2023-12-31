package com.onlineshop.domain;

import lombok.*;

import javax.persistence.*;

@Entity (name = "products_in_shop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productInShopId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Shop.class)
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private Integer quantity;
}
