package com.konstantinbulygin.onlinestore.controllers.restapi;

import com.konstantinbulygin.onlinestore.model.Customer;
import com.konstantinbulygin.onlinestore.model.Order;
import com.konstantinbulygin.onlinestore.model.restmodel.NewCustomer;
import com.konstantinbulygin.onlinestore.model.restmodel.NewOrder;
import com.konstantinbulygin.onlinestore.service.CustomerService;
import com.konstantinbulygin.onlinestore.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/data")
@CrossOrigin(origins = "*")
@Api("Main controller")
public class MainRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/show/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Return one order")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/show/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Return one customer")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/show/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Returns list of orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orderList = orderService.findAll();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping(value = "/show/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Returns list of customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customerList = customerService.findAll();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping(value = "/new/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register new customer and return customer info")
    public ResponseEntity<Customer> newCustomer(@RequestBody NewCustomer newCustomer) {

        Customer customer = new Customer();
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerDate(LocalDateTime.now());

        Customer savedCustomer = customerService.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @PostMapping(value = "/new/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register new order and return order info")
    public ResponseEntity<Order> newOrder(@RequestBody NewOrder newOrder) {

        Order order = new Order();
        order.setCustomerId(newOrder.getCustomerId());
        order.setOrderCost(newOrder.getOrderCost());
        order.setOderDate(LocalDateTime.now());

        Order savedOrder = orderService.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @PatchMapping(value = "/update/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update customer info and returns updated customer info")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @PatchMapping(value = "/update/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update order info and returns updated order info")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/order/{id}")
    @ApiOperation("Delete order by id")
    public ResponseEntity<Order> deleteOrderById(@PathVariable int id) {
        Order deletedOrder = orderService.deleteByOrderId(id);
        return new ResponseEntity<>(deletedOrder, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/customer/{id}")
    @ApiOperation("Delete customer by id")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable int id) {
        Customer deletedCustomer = customerService.deleteByCustomerId(id);
        return new ResponseEntity<>(deletedCustomer, HttpStatus.ACCEPTED);
    }


}
