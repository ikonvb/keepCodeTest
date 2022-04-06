package com.konstantinbulygin.onlinestore.service;

import com.konstantinbulygin.onlinestore.model.Order;
import com.konstantinbulygin.onlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderRepoService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order deleteByOrderId(Integer id) {
        return orderRepository.deleteByOrderId(id);
    }

    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }
}
