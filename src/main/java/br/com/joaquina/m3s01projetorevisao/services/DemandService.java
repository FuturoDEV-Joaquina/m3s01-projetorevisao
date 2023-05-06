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
import java.util.List;
import java.util.Optional;

@Service
public class DemandService {

    private ProductService productService;
    private CustomerService customerService;
    private DemandRepository demandRepository;

    public DemandService(
            ProductService productService,
            DemandRepository demandRepository,
            CustomerService customerService
    ) {
        this.productService = productService;
        this.customerService = customerService;
        this.demandRepository = demandRepository;
    }

    public Demand create(Demand demand) throws Exception {

        demand.setId(null);
        demand.setDateOrdered(new Date());

        if (demand.getCustomer() == null) {
            throw new Exception("Customer is required!");
        }

        demand.setCustomer(customerService.getById(demand.getCustomer().getId()));

        if (demand.getItems() == null || demand.getItems().isEmpty()) {
            throw new Exception("Items is required!");
        }

        BigDecimal totalItems = BigDecimal.ZERO;
        for (DemandItem item : demand.getItems()) {
            item.setDemand(demand);
            if (item.getProduct() == null) {
                throw new Exception("Product is required!");
            }

            item.setProduct(productService.getById(item.getProduct().getId()));

            if (item.getQty() == null || item.getQty().compareTo(BigDecimal.ZERO) <= 0) {
                throw new Exception("Qty should be greater than ZERO!");
            }

            if (item.getProduct().getQtyStock().compareTo(item.getQty()) < 0) {
                throw new Exception("Qty Stock insufficient!");
            }

            item.setPrice(item.getProduct().getPrice());
            item.setTotal(item.getQty().multiply(item.getPrice()).setScale(2, RoundingMode.HALF_DOWN));

            totalItems = totalItems.add(item.getTotal());
        }
        demand.setTotalItems(totalItems);
        demand.setTotal(totalItems);

        demand = demandRepository.save(demand);

        // Diminiuir estoque
        for (DemandItem item : demand.getItems()) {
            productService.substractStock(item.getProduct(), item.getQty());
        }

        return demand;
    }

    public List<Demand> getAll() {
        return demandRepository.findAll();
    }

    public Demand getById(Long id) throws Exception {
        Optional<Demand> demandOpt = demandRepository.findById(id);
        if (demandOpt.isEmpty()) {
            throw new Exception("Demand not found!");
        }
        return demandOpt.get();
    }

}
