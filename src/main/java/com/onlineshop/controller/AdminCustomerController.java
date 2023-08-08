package com.onlineshop.controller;

import com.onlineshop.controller.dto.CustomerDTO;
import com.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
        CustomerDTO customerDTOFinal = customerService.update(id, customerDTO);
        if (customerDTOFinal != null) {
            return ResponseEntity.ok(customerDTOFinal);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id) {
        CustomerDTO result = customerService.delete(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
            }
        return ResponseEntity.ok(result);
    }
}
