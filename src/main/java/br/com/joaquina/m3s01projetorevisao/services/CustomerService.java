package br.com.joaquina.m3s01projetorevisao.services;

import br.com.joaquina.m3s01projetorevisao.entities.Customer;
import br.com.joaquina.m3s01projetorevisao.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) throws Exception {

        if (customer.getId() != null && !customerRepository.existsById(customer.getId())) {
            throw new Exception("Customer not found!");
        }

        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new Exception("Name is required!");
        }

        if (customer.getTaxId() == null || customer.getTaxId().isEmpty()) {
            throw new Exception("Tax Id is required!");
        }

        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new Exception("Email is required!");
        }

        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            throw new Exception("Phone is required!");
        }

        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            throw new Exception("Address is required!");
        }

        if (customer.getNumber() == null || customer.getNumber().isEmpty()) {
            throw new Exception("Number is required!");
        }

        if (customer.getNeighborhood() == null || customer.getNeighborhood().isEmpty()) {
            throw new Exception("Neighborhood is required!");
        }

        if (customer.getCity() == null || customer.getCity().isEmpty()) {
            throw new Exception("City is required!");
        }

        if (customer.getState() == null || customer.getState().isEmpty()) {
            throw new Exception("State is required!");
        }

        customer = customerRepository.save(customer);

        return customer;

    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) throws Exception {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new Exception("Customer not found!");
        }
        return customerOpt.get();
    }

    public boolean delete(Long id) throws Exception {
        Customer customer = getById(id);
        try {
            customerRepository.delete(customer);
            return true;
        } catch (Exception e) {
            throw new Exception("Something went wrong! BOOOMMM.");
        }
    }

}
