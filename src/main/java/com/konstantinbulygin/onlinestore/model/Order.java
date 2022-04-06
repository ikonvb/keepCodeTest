package com.konstantinbulygin.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "customer_id")
    Integer customerId;

    @Column(name = "order_cost")
    Double orderCost;

    @DateTimeFormat
    @Column(name = "order_date")
    LocalDateTime oderDate;

}
