package com.hackathon.inditex.shared.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hackathon.inditex.dtos.CreateOrderResponse;
import com.hackathon.inditex.dtos.OrderAssignmentResponse;
import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.services.OrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrderControllerShared {
	
	private final OrderService orderService;

    public ResponseEntity<CreateOrderResponse> createOrder(Order orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }
    
    public ResponseEntity<List<Order>> getAllOrders() {
    	return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    public ResponseEntity<Map<String, List<OrderAssignmentResponse>>> assignOrders() {
        return ResponseEntity.ok(Map.of("processed-orders", orderService.assignPendingOrders()));
    }

}
