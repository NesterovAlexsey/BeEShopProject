package com.onlineshop;

import com.onlineshop.controller.dto.CountryDTO;
import com.onlineshop.controller.dto.CustomerDTO;
import com.onlineshop.repository.CountryRepository;
import com.onlineshop.service.CountryService;
import com.onlineshop.service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CountryService countryService;

    @Test
//    @Order(1)
    public void addCustomerPositiveTest() {
        CountryDTO countryDTO = new CountryDTO(null, "France");
        CountryDTO countryFrance = countryService.add(countryDTO);

        CustomerDTO customerDTO = new CustomerDTO(
                null, "Test", "Test address", countryFrance);
        CustomerDTO customerCheck = customerService.add(customerDTO);

        Assertions.assertEquals("Test", customerCheck.getCustomerName());
        Assertions.assertEquals("Test address", customerCheck.getAddress());
        Assertions.assertEquals("France", customerCheck.getCountry().getCountryName());
    }

//    @Test
//    @Order(2)
//    public void findListOfAllCustomerPositiveTest() {
//        CountryDTO countryDTO = new CountryDTO(null, "USA");
//        CountryDTO countryUSA = countryService.add(countryDTO)
//        CustomerDTO customerDTO = new CustomerDTO(
//                null, "Test2", "Test address2", countryUSA);
//        customerService.add(customerDTO);
//
//        List<CustomerDTO> customerDTOS = customerService.findAll();
//
//        Assertions.assertEquals(2, customerDTOS.size());
//    }
}
