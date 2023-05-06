package br.com.joaquina.m3s01projetorevisao.services;

import br.com.joaquina.m3s01projetorevisao.entities.Product;
import br.com.joaquina.m3s01projetorevisao.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) throws Exception {

        if (product.getId() != null && !productRepository.existsById(product.getId())) {
            throw new Exception("Product not found!");
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new Exception("Name is required!");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Price should be greater than ZERO!");
        }

        if (product.getQtyStock() == null || product.getQtyStock().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Qty Stock should be greater than ZERO!");
        }

        product = productRepository.save(product);

        return product;

    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) throws Exception {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new Exception("Product not found!");
        }
        return productOpt.get();
    }

    public boolean delete(Long id) throws Exception {
        Product product = getById(id);
        try {
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            throw new Exception("Something went wrong! BOOOMMM.");
        }
    }

    public void substractStock(Product product, BigDecimal qty) throws Exception {
        product = getById(product.getId());
        product.setQtyStock(product.getQtyStock().subtract(qty));
        productRepository.save(product);
    }

}
