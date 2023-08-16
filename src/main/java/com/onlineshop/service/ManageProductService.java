package com.onlineshop.service;

import com.onlineshop.dto.OrderDTO;
import com.onlineshop.dto.ProductInShopDTO;
import com.onlineshop.domain.*;
import com.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageProductService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInShopRepository productInShopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //Method will add products to the shop with given id
    public ProductInShopDTO productToShop(Integer shopId, ProductInShopDTO products) {
        //todo create class with bringing back error message
        //todo separate all the not useful or same code parts
        //todo create repository in control for documentation
        //todo the logic checking of income parameters
        //todo added the validation of income data

        //with first step, we try to find in database models shop by id and check, if we will have the correct Shop
        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isEmpty()){
            return null;
        }

        //then we try to get list of products in that shop using
        List<ProductInShop> productsInShop = productInShopRepository.findByShop(optionalShop.get());
        if(productsInShop.isEmpty()) {
            return null;
        }

        for (ProductInShopDTO.ProductItem productItem : products.getProductItems()) {
            Optional<Product> optProduct = productRepository.findById(productItem.getProductId());
            if (optProduct.isEmpty()) {
                return null;
            }

            boolean isProductFound = false;
            for (ProductInShop productInShop : productsInShop) {
                if (productInShop.getProduct().equals(optProduct.get())) {
                    productInShop.setQuantity(productInShop.getQuantity() + productItem.getQuantity());
                    productInShopRepository.save(productInShop);
                    isProductFound = true;
                    break;
                }
            }

            if (!isProductFound) {
                ProductInShop productInShop =new ProductInShop();
                productInShop.setShop(optionalShop.get());
                productInShop.setProduct(optProduct.get());
                productInShop.setQuantity(productItem.getQuantity());
                productInShop = productInShopRepository.save(productInShop);
                productsInShop.add(productInShop);
            }

        }

        productInShopRepository.saveAll(productsInShop);
        return products;
    }

    public ProductInShopDTO findByShop(Integer shopId) {
        Optional<Shop> optShop = shopRepository.findById(shopId);
        // TODO check if shop exists

        List<ProductInShop> productsInShop = productInShopRepository.findByShop(optShop.get());
        ProductInShopDTO productInShopDTO = new ProductInShopDTO();
        for (ProductInShop product : productsInShop) {
            productInShopDTO.addProductItem(product.getProduct().getProductId(), product.getQuantity());
        }

        return productInShopDTO;
    }

    public OrderDTO changeOrderState(Integer orderId, OrderState orderState) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        //todo check if order exists
        //todo if we can to change state

        Order order = optOrder.get();
        order.setState(orderState);
        orderRepository.save(order);

        return OrderDTO.getInstance(order);
    }

    public OrderDTO getProductsByOrder(Integer orderId) {
        //todo rewrite for one base request
        //todo optimization:
        Optional<Order> optOrder = orderRepository.findById(orderId);
        //todo check if order exists
        //todo if order has property state

        Order order = optOrder.get();
        List<ProductInShop> productsInShop = productInShopRepository.findByShop(order.getShop());
        List<OrderDetail> productsInOrder = orderDetailRepository.findByOrder(order);

        for (OrderDetail orderDetail : productsInOrder) {
            boolean foundProduct = false;
            for (ProductInShop productInShop : productsInShop) {
                if (orderDetail.getProduct().equals(productInShop.getProduct())) {
                    productInShop.setQuantity(productInShop.getQuantity()-orderDetail.getQuantity());
                    //todo if quantity not negative after operation
                    productInShopRepository.save(productInShop);
                    foundProduct = true;
                    break;
                }
            }
            if(!foundProduct) {
                //todo log error
                return null;
            }
        }
        order.setState(OrderState.DELIVERED);
        orderRepository.save(order);
        return OrderDTO.getInstance(order);
    }
}
