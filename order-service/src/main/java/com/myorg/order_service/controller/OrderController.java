package com.myorg.order_service.controller;


import com.myorg.order_service.dto.OrderRequest;
import com.myorg.order_service.dto.OrderResponse;
import com.myorg.order_service.model.Order;
import com.myorg.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order created successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> fetchAllOrders(){
        return orderService.fetchAllOrders();
    }
}
