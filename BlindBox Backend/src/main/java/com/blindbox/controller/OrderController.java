package com.blindbox.controller;

import com.blindbox.model.Order;
import com.blindbox.response.ResponseData;
import com.blindbox.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Management System", description = "Operations pertaining to orders in the Order Management System")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @Operation(summary = "Create a new order", description = "Add a new order to the system")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Create a new order from cart
    @Operation(summary = "Create an order from cart", description = "Create an order from the user's cart to buy product")
    @PostMapping("/create-from-cart/{userId}")
    public ResponseEntity<ResponseData> createOrderFromCart(@PathVariable Integer userId) {
        try {
            Order order = orderService.createOrderFromCart(userId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Order created successfully from cart", order, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to create order from cart" + e.getMessage(), null, null));
        }
    }

    // Update an existing order
    @Operation(summary = "Update an existing order", description = "Update an existing order using its ID")
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(orderId, order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Pay by user balance
    @Operation(summary = "Pay for order by user balance", description = "Pay for the order by deducting the user's balance")
    @PostMapping("/pay/{userId}/{orderId}")
    public ResponseEntity<ResponseData> payForOrderByUserBalance(@PathVariable Integer userId, @PathVariable Integer orderId) {
        try {
            boolean paymentSuccess = orderService.payForOrderByUserBalance(userId, orderId);
            if (paymentSuccess) {
                return ResponseEntity.ok(new ResponseData(200, true, "Payment successful", null, null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseData(400, false, "Insufficient balance", null, null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to process payment", null, null));
        }
    }

    // Delete an order
    @Operation(summary = "Delete an order by ID")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseData> deleteOrder(@PathVariable("orderId") Integer orderId) {
        if (orderService.deleteOrder(orderId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseData(204, true, "Order deleted successfully", null, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseData(404, false, "Order not found", null, null));
    }

    // Get all orders
    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders")
    @GetMapping
    public ResponseEntity<ResponseData> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return ResponseEntity.ok(new ResponseData(200, true, "Orders retrieved successfully", orders, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve orders" + e.getMessage(), null, null));
        }
    }

    // Get order by ID
    @Operation(summary = "Get an order by ID", description = "Retrieve a single order using its ID")
    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseData> getOrderById(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(new ResponseData(200, true, "Order retrieved successfully", order, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseData(404, false, "Order not found", null, null));
    }
}
