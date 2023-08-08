package com.onlineshop.service;

import com.onlineshop.controller.dto.ShopDTO;
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
