package com.hackathon.inditex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.inditex.entities.Order;
import com.hackathon.inditex.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByStatus(OrderStatus status);
}
