package com.konstantinbulygin.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Integer customerId;

    @NotNull
    @NotEmpty
    @Column(name = "customer_name")
    String customerName;

    @DateTimeFormat
    @Column(name = "customer_date")
    LocalDateTime customerDate;


}
