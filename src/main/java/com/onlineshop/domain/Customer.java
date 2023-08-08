package com.onlineshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
    @JoinColumn(name = "countryId", nullable = false)
    private Country country;
}