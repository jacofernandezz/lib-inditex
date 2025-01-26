package com.hackathon.inditex.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.inditex.dtos.CreateOrderResponse;
import com.hackathon.inditex.dtos.OrderAssignmentResponse;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.enums.CenterStatus;
import com.hackathon.inditex.enums.OrderStatus;
import com.hackathon.inditex.repositories.CenterRepository;
import com.hackathon.inditex.repositories.OrderRepository;
import com.hackathon.inditex.utils.DistanceCalculator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final CenterRepository centerRepository;

	@Override
	public CreateOrderResponse createOrder(Order orderRequest) {
		return buildCreateOrderResponse(orderRepository.save(orderRequest));
	}

	@Override
	public List<Order> getAllOrders() {
	    return orderRepository.findAll();
	}

	private CreateOrderResponse buildCreateOrderResponse(Order order) {
	    return new CreateOrderResponse(
	            order.getId(),
	            order.getCustomerId(),
	            order.getSize(),
	            order.getAssignedCenter(),
	            order.getCoordinates(),
	            order.getStatus(),
	            "Order created successfully in PENDING status."
	    );
	}

	@Override
	@Transactional
	public List<OrderAssignmentResponse> assignPendingOrders() {
	    List<Order> pendingOrders = orderRepository.findByStatus(OrderStatus.PENDING);
	    List<Center> availableCenters = centerRepository.findByStatus(CenterStatus.AVAILABLE);

	    List<OrderAssignmentResponse> responses = pendingOrders.stream()
	            .map(order -> assignOrder(order, availableCenters))
	            .toList();

	    orderRepository.saveAll(pendingOrders);

	    return responses;
	}

	private OrderAssignmentResponse assignOrder(Order order, List<Center> availableCenters) {

	    return findClosestAvailableCenter(order, availableCenters)
	            .map(center -> processSuccessfulAssignment(order, center))
	            .orElseGet(() -> processFailedAssignment(order, availableCenters));
	}

	private Optional<Center> findClosestAvailableCenter(Order order, List<Center> availableCenters) {

	    Map<Center, Double> distances = new HashMap<>();
	    
	    availableCenters.stream()
	            .filter(center -> centerCanHandleOrder(center, order))
	            .forEach(center -> {
	                double distance = DistanceCalculator.calculateHaversineDistance(order.getCoordinates(), center.getCoordinates());
	                distances.put(center, distance);
	            });
	    
	    return distances.entrySet().stream()
	            .min(Map.Entry.comparingByValue())
	            .map(Map.Entry::getKey);
	}

	private boolean centerCanHandleOrder(Center center, Order order) {
	    return center.getCapacity().contains(order.getSize()) && center.getCurrentLoad() < center.getMaxCapacity();
	}

	private OrderAssignmentResponse processSuccessfulAssignment(Order order, Center center) {
	    double distance = DistanceCalculator.calculateHaversineDistance(order.getCoordinates(), center.getCoordinates());
	    updateOrderAndCenter(order, center);
	    return buildOrderAssignmentResponse(order, distance, center.getName(), null);
	}

	private void updateOrderAndCenter(Order order, Center center) {
	    order.setAssignedCenter(center.getName());
	    order.setStatus(OrderStatus.ASSIGNED);
	    center.setCurrentLoad(center.getCurrentLoad() + 1);
	}

	private OrderAssignmentResponse processFailedAssignment(Order order, List<Center> availableCenters) {
	    String message = determineFailureMessage(order, availableCenters);
	    return buildOrderAssignmentResponse(order, null, null, message);
	}

	private String determineFailureMessage(Order order, List<Center> availableCenters) {
	    return availableCenters.stream().anyMatch(center -> center.getCapacity().contains(order.getSize()))
	            ? "All centers are at maximum capacity."
	            : "No available centers support the order type.";
	}

	private OrderAssignmentResponse buildOrderAssignmentResponse(Order order, Double distance, String centerName, String message) {
	    return new OrderAssignmentResponse(
	            order.getId(),
	            distance,
	            centerName,
	            order.getStatus(),
	            message
	    );
	}

}
