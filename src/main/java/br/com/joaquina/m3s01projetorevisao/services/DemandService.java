package br.com.joaquina.m3s01projetorevisao.services;

import br.com.joaquina.m3s01projetorevisao.entities.Customer;
import br.com.joaquina.m3s01projetorevisao.entities.Demand;
import br.com.joaquina.m3s01projetorevisao.entities.DemandItem;
import br.com.joaquina.m3s01projetorevisao.entities.Product;
import br.com.joaquina.m3s01projetorevisao.repositories.CustomerRepository;
import br.com.joaquina.m3s01projetorevisao.repositories.DemandRepository;
import br.com.joaquina.m3s01projetorevisao.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class DemandService {

    private DemandRepository demandRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    public DemandService(
            DemandRepository demandRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository
    ) {
        this.demandRepository = demandRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Demand create(Demand demand) throws Exception {

        demand.setId(null);
        demand.setDateOrdered(new Date());

        if (demand.getCustomer() == null) {
            throw new Exception("Customer is required!");
        }

        Optional<Customer> customerOpt = customerRepository.findById(demand.getCustomer().getId());
        if (customerOpt.isEmpty()) {
            throw new Exception("Customer not found!");
        }
        demand.setCustomer(customerOpt.get());

        if (demand.getItems() == null || demand.getItems().isEmpty()) {
            throw new Exception("Items is required!");
        }

        BigDecimal totalItems = BigDecimal.ZERO;
        for (DemandItem item : demand.getItems()) {
            item.setDemand(demand);
            if (item.getProduct() == null) {
                throw new Exception("Product is required!");
            }

            Optional<Product> productOpt = productRepository.findById(item.getProduct().getId());
            if (productOpt.isEmpty()) {
                throw new Exception("Product not found!");
            }
            item.setProduct(productOpt.get());

            if (item.getQty() == null || item.getQty().compareTo(BigDecimal.ZERO) <= 0) {
                throw new Exception("Qty should be greater than ZERO!");
            }

            item.setPrice(item.getProduct().getPrice());
            item.setTotal(item.getQty().multiply(item.getPrice()).setScale(2, RoundingMode.HALF_DOWN));

            totalItems = totalItems.add(item.getTotal());
        }
        demand.setTotalItems(totalItems);
        demand.setTotal(totalItems);

        demand = demandRepository.save(demand);

        return demand;
    }

}
