package com.onlineshop.service;

import com.onlineshop.dto.ShopDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ShopServiceResponse {
    private ShopDTO shopDTO;
    private Integer responseResult;
}
