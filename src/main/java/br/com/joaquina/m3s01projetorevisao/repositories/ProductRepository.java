package br.com.joaquina.m3s01projetorevisao.repositories;

import br.com.joaquina.m3s01projetorevisao.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
