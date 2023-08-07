package com.onloneshop.service;

import com.onloneshop.controller.dto.CustomerDTO;
import com.onloneshop.domain.Country;
import com.onloneshop.domain.Customer;
import com.onloneshop.repository.CountryRepository;
import com.onloneshop.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customersDTO = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> customersDTO.add(CustomerDTO.getInstance(customer)));
        return customersDTO;
    }

    public CustomerDTO add(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        //find country
        Integer countryID = customerDTO.getCountry().getCountryId();
        log.debug("Find Country id with: {}", countryID);

        Optional<Country> optCountry = countryRepository.findById(countryID);
        //check if country present
        if (optCountry.isEmpty()) {
            log.error("Not found category categoryId: {}", countryID);
            return null;
        }

        //add all parameters
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCountry(optCountry.get());
        customer.setAddress(customerDTO.getAddress());

        customer = customerRepository.save(customer);

        return CustomerDTO.getInstance(customer);
    }

    public CustomerDTO update(Integer id, CustomerDTO customerDTO) {
        //find object, which we want to update
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()){
            return null;
        }
        Customer customer = optCustomer.get();

        //find country
        Integer countryID = customerDTO.getCountry().getCountryId();
        log.debug("Find Country id with: {}", countryID);

        Optional<Country> optCountry = countryRepository.findById(countryID);
        //check if country present
        if (optCountry.isEmpty()) {
            log.error("Not found category categoryId: {}", countryID);
            return null;
        }

        //add all parameters
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCountry(optCountry.get());
        customer.setAddress(customerDTO.getAddress());

        customer = customerRepository.save(customer);

        return CustomerDTO.getInstance(customer);
    }

    public CustomerDTO delete(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer delCustomer = customer.get();
            customerRepository.delete(delCustomer);
            return CustomerDTO.getInstance(delCustomer);
        }
        return null;
    }
}
