package com.konstantinbulygin.onlinestore.controllers.restapi;

import com.konstantinbulygin.onlinestore.model.Customer;
import com.konstantinbulygin.onlinestore.model.Order;
import com.konstantinbulygin.onlinestore.model.restmodel.NewCustomer;
import com.konstantinbulygin.onlinestore.model.restmodel.NewOrder;
import com.konstantinbulygin.onlinestore.service.CustomerRepoService;
import com.konstantinbulygin.onlinestore.service.OrderRepoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "*")
@Api("Main controller")
public class MainRestController {

    @Autowired
    OrderRepoService orderRepoService;

    @Autowired
    CustomerRepoService customerRepoService;

    @GetMapping(value = "/show/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Return one order")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderRepoService.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/show/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Return one customer")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Optional<Customer> customer = customerRepoService.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/show/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Returns list of orders")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orderList = orderRepoService.findAll();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping(value = "/show/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Returns list of customers")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customerList = customerRepoService.findAll();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping(value = "/new/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register new customer and return customer info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> newCustomer(@RequestBody NewCustomer newCustomer) {

        Customer customer = new Customer();
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerDate(LocalDateTime.now());

        Customer savedCustomer = customerRepoService.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @PostMapping(value = "/new/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register new order and return order info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> newOrder(@RequestBody NewOrder newOrder) {

        Order order = new Order();
        order.setCustomerId(newOrder.getCustomerId());
        order.setOrderCost(newOrder.getOrderCost());
        order.setOderDate(LocalDateTime.now());

        Order savedOrder = orderRepoService.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @PatchMapping(value = "/update/customer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update customer info and returns updated customer info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> updateCustomer(@RequestBody NewCustomer newCustomer, @PathVariable int id) {

        Optional<Customer> oldCustomer = customerRepoService.findById(id);

        if (oldCustomer.isPresent()) {
            oldCustomer.get().setCustomerName(newCustomer.getCustomerName());
            Customer savedCustomer = customerRepoService.save(oldCustomer.get());
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/update/order/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update order info and returns updated order info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateOrder(@RequestBody NewOrder newOrder, @PathVariable int id) {

        Optional<Order> oldOrder = orderRepoService.findById(id);
        Optional<Customer> customer = customerRepoService.findById(newOrder.getCustomerId());

        if (oldOrder.isPresent() && customer.isPresent()) {
            oldOrder.get().setOrderCost(newOrder.getOrderCost());
            oldOrder.get().setCustomerId(newOrder.getCustomerId());
            Order savedOrder = orderRepoService.save(oldOrder.get());
            return new ResponseEntity<>(savedOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/order/{id}")
    @ApiOperation("Delete order by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> deleteOrderById(@PathVariable int id) {
        Order deletedOrder = orderRepoService.deleteByOrderId(id);
        return new ResponseEntity<>(deletedOrder, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/customer/{id}")
    @ApiOperation("Delete customer by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable int id) {
        Customer deletedCustomer = customerRepoService.deleteByCustomerId(id);
        return new ResponseEntity<>(deletedCustomer, HttpStatus.ACCEPTED);
    }
}
