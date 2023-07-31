package com.onloneshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    private String supplierName;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name = "countryId", nullable = false)
    private Country country;
}