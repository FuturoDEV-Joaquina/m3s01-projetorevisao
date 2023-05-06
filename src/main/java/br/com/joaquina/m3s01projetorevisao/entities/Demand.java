package br.com.joaquina.m3s01projetorevisao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Demand {

    @Id
    @Column(name = "demand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateOrdered = new Date();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalItems;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DemandItem> items = new ArrayList<>();

}


/*


{
    "id": 5,
    "dateOrdered": "2023-05-06T00:55:59.847+00:00",
    "customer": {
        "id": 1,
        "name": "Gabriel Augustin",
        "taxId": "012.345.678-90",
        "email": "gabriel@email.com",
        "phone": "(11) 99911-2233",
        "address": "Rua dos bobos",
        "complement": null,
        "number": "0",
        "neighborhood": "Centro",
        "city": "Chapecó",
        "state": "SC"
    },
    "totalItems": 479.80,
    "total": 479.80,
    "items": [
        {
            "id": 7,
            "demand": {
                "id": 5,
                "dateOrdered": "2023-05-06T00:55:59.847+00:00",
                "customer": {
                    "id": 1,
                    "name": "Gabriel Augustin",
                    "taxId": "012.345.678-90",
                    "email": "gabriel@email.com",
                    "phone": "(11) 99911-2233",
                    "address": "Rua dos bobos",
                    "complement": null,
                    "number": "0",
                    "neighborhood": "Centro",
                    "city": "Chapecó",
                    "state": "SC"
                },
                "totalItems": 479.80,
                "total": 479.80,
                "items": [
                    {
                        "id": 7,
                        "demand": {
                            "id": 5,
                            "dateOrdered": "2023-05-06T00:55:59.847+00:00",
                            "customer": {
                                "id": 1,
                                "name": "Gabriel Augustin",
                                "taxId": "012.345.678-90",
                                "email": "gabriel@email.com",
                                "phone": "(11) 99911-2233",
                                "address": "Rua dos bobos",
                                "complement": null,
                                "number": "0",
                                "neighborhood": "Centro",
                                "city": "Chapecó",
                                "state": "SC"
                            },
                            "totalItems": 479.80,
                            "total": 479.80,
                            "items": [
                                {
                                    "id": 7,
                                    "demand": {
                                        "id": 5,
                                        "dateOrdered": "2023-05-06T00:55:59.847+00:00",
                                        "customer": {
                                            "id": 1,
                                            "name": "Gabriel Augustin",
                                            "taxId": "012.345.678-90",
                                            "email": "gabriel@email.com",
                                            "phone": "(11) 99911-2233",
                                            "address": "Rua dos bobos",
                                            "complement": null,
                                            "number": "0",
                                            "neighborhood": "Centro",
                                            "city": "Chapecó",
                                            "state": "SC"
                                        },
                                        "totalItems": 479.80,
                                        "total": 479.80,
                                        "items": [
                                            {
                                                "id": 7,
                                                "demand": {

                                                },
                                                "product": {
                                                    "id": 1,
                                                    "name": "Fones de ouvido",
                                                    "description": null,
                                                    "price": 300.00,
                                                    "qtyStock": 20.0000
                                                },
                                                "price": 300.00,
                                                "qty": 1,
                                                "total": 300.00
                                            },
                                            {
                                                "id": 8,
                                                "product": {
                                                    "id": 2,
                                                    "name": "Camiseta",
                                                    "description": "Camiseta do LAB 365 \\o/",
                                                    "price": 89.90,
                                                    "qtyStock": 100.0000
                                                },
                                                "price": 89.90,
                                                "qty": 2,
                                                "total": 179.80
                                            }
                                        ]
                                    },
                                    "product": {
                                        "id": 1,
                                        "name": "Fones de ouvido",
                                        "description": null,
                                        "price": 300.00,
                                        "qtyStock": 20.0000
                                    },
                                    "price": 300.00,
                                    "qty": 1,
                                    "total": 300.00
                                },
                                {
                                    "id": 8,
                                    "product": {
                                        "id": 2,
                                        "name": "Camiseta",
                                        "description": "Camiseta do LAB 365 \\o/",
                                        "price": 89.90,
                                        "qtyStock": 100.0000
                                    },
                                    "price": 89.90,
                                    "qty": 2,
                                    "total": 179.80
                                }
                            ]
                        },
                        "product": {
                            "id": 1,
                            "name": "Fones de ouvido",
                            "description": null,
                            "price": 300.00,
                            "qtyStock": 20.0000
                        },
                        "price": 300.00,
                        "qty": 1,
                        "total": 300.00
                    },
                    {
                        "id": 8,
                        "product": {
                            "id": 2,
                            "name": "Camiseta",
                            "description": "Camiseta do LAB 365 \\o/",
                            "price": 89.90,
                            "qtyStock": 100.0000
                        },
                        "price": 89.90,
                        "qty": 2,
                        "total": 179.80
                    }
                ]
            },
            "product": {
                "id": 1,
                "name": "Fones de ouvido",
                "description": null,
                "price": 300.00,
                "qtyStock": 20.0000
            },
            "price": 300.00,
            "qty": 1,
            "total": 300.00
        },
        {
            "id": 8,
            "product": {
                "id": 2,
                "name": "Camiseta",
                "description": "Camiseta do LAB 365 \\o/",
                "price": 89.90,
                "qtyStock": 100.0000
            },
            "price": 89.90,
            "qty": 2,
            "total": 179.80
        }
    ]
}

 */
