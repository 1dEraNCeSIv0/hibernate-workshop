package de.andrena.hibernateworkshop.service.customer;

import de.andrena.hibernateworkshop.exception.NotFoundException;
import de.andrena.hibernateworkshop.persistence.customer.CustomerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(CustomerDto::toDto)
                .orElseThrow(() -> new NotFoundException("No customer found for id %s.", id));
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerByName(String name) {
        return customerRepository.findByName(name)
                .map(CustomerDto::toDto)
                .orElseThrow(() -> new NotFoundException("No customer found for name %s.", name));
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDto::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getCustomersUsingEntityGraph() {
        return customerRepository.findAllUsingEntityGraph().stream()
                .map(CustomerDto::toDto)
                .toList();
    }

}
