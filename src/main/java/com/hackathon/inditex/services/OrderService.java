package com.hackathon.inditex.services;

import java.util.List;

import com.hackathon.inditex.dtos.CreateOrderResponse;
import com.hackathon.inditex.dtos.OrderAssignmentResponse;
import com.hackathon.inditex.entities.Order;

public interface OrderService {

	CreateOrderResponse createOrder(Order orderRequest);

	List<Order> getAllOrders();

	List<OrderAssignmentResponse> assignPendingOrders();

}
