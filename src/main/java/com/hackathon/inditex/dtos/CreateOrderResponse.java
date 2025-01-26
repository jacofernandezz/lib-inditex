package com.hackathon.inditex.dtos;

import com.hackathon.inditex.entities.Coordinates;
import com.hackathon.inditex.enums.OrderStatus;

public class CreateOrderResponse {
    private final Long orderId;
    private final Long customerId;
    private final String size;
    private final String assignedLogisticsCenter;
    private final Coordinates coordinates;
    private final OrderStatus status;
    private final String message;

    public CreateOrderResponse(Long orderId, Long customerId, String size, String assignedLogisticsCenter, 
                               Coordinates coordinates, OrderStatus status, String message) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.size = size;
        this.assignedLogisticsCenter = assignedLogisticsCenter;
        this.coordinates = coordinates;
        this.status = status;
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getSize() {
        return size;
    }

    public String getAssignedLogisticsCenter() {
        return assignedLogisticsCenter;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
