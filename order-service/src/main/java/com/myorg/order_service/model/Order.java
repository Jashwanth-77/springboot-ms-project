package com.myorg.order_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL , fetch =  FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderLineItems> orderLineItemsList;
    
}
