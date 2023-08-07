package com.onloneshop.controller;

import com.onloneshop.controller.dto.CustomerDTO;
import com.onloneshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/customer")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/add")
    public CustomerDTO add(@RequestBody CustomerDTO customerDTO) {
        return customerService.add(customerDTO);
    }

    @PutMapping("/update/{id}")
    public CustomerDTO update(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
        return customerService.update(id, customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public CustomerDTO delete(@PathVariable Integer id) {
        return customerService.delete(id);
    }
}
