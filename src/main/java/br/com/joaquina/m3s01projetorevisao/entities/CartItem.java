package br.com.joaquina.m3s01projetorevisao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal qty;

}
