package com.onlineshop;

import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.repository.ShopRepository;
import com.onlineshop.service.ShopService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Test
    @Order(1)
    public void addShopPositiveTest() {
        ShopDTO shopDTO = new ShopDTO(null, "First Test Shop");
        ShopDTO firstShop = shopService.addShop(shopDTO);

        Assertions.assertEquals("First Test Shop", firstShop.getShopName());
    }

    @Test
    @Order(2)
    public void findAllShopsPositiveTest() {
        List<ShopDTO> shops = shopService.findAllShops();

        Assertions.assertEquals(1, shops.size());
    }

    @Test
    @Order(3)
    public void updateShopPositiveTest() {
        ShopDTO secondShop = new ShopDTO(null, "Second Test Shop");
        ShopDTO updateShop = shopService.update(1, secondShop);

        Assertions.assertEquals("Second Test Shop", updateShop.getShopName());
    }

//    @Test
//    @Order(4)
//    public void updateShopNegativeTestWithIncorrectId() {
//        ShopDTO secondShop = new ShopDTO(null, "Second Test Shop");
//        ShopDTO updateShop = shopService.update(4, secondShop);
//
//        Assertions.assertNull(updateShop.getShopName());
//    }

    @Test
    @Order(4)
    public void deleteShopPositiveTest() {
        ShopDTO deleteShop = shopService.deleteShop(1);
        List<ShopDTO> shopCheckList = shopService.findAllShops();

        Assertions.assertEquals("Second Test Shop", deleteShop.getShopName());
        Assertions.assertEquals(0, shopCheckList.size());
    }
}
