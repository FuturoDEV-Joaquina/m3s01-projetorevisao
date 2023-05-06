package br.com.joaquina.m3s01projetorevisao.controllers;

import br.com.joaquina.m3s01projetorevisao.entities.Customer;
import br.com.joaquina.m3s01projetorevisao.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> get() {
        return customerService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
        try {
            Customer customer = customerService.getById(id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Customer customer) {
        try {
            customer.setId(null);
            return ResponseEntity.ok(customerService.save(customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            customer.setId(id);
            return ResponseEntity.ok(customerService.save(customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
