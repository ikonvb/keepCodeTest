package com.konstantinbulygin.onlinestore.repository;

import com.konstantinbulygin.onlinestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order deleteByOrderId(int id);
}
