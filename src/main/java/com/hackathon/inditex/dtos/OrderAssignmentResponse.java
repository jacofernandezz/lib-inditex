package com.hackathon.inditex.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackathon.inditex.enums.OrderStatus;

public class OrderAssignmentResponse {
    private final Long orderId;
    private final Double distance;
    private final String assignedLogisticsCenter;
    private final OrderStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    
    public OrderAssignmentResponse(Long orderId, Double distance, String assignedLogisticsCenter, OrderStatus status, String message) {
        this.orderId = orderId;
        this.distance = distance;
        this.assignedLogisticsCenter = assignedLogisticsCenter;
        this.status = status;
        this.message = message;
    }

	public Long getOrderId() {
		return orderId;
	}

	public Double getDistance() {
		return distance;
	}

	public String getAssignedLogisticsCenter() {
		return assignedLogisticsCenter;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
    
}
