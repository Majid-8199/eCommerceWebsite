package com.ecom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.OrderDTO;
import com.ecom.entity.Order;
import com.ecom.enums.OrderStatus;
import com.ecom.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderDTO> viewAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDTO> orderDTOs = orders.stream()
                .map(Order::getOrderDto)
                .collect(Collectors.toList());
		return orderDTOs;
	}
	
	public List<OrderDTO> viewOrdersbyUserId(long userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		List<OrderDTO> orderDTOs = orders.stream()
                .map(Order::getOrderDto)
                .collect(Collectors.toList());
		return orderDTOs;
	}
	
	public OrderDTO updateOrderStatus(long orderId, OrderStatus orderStatus) throws Exception {
	    Optional<Order> order = orderRepository.findById(orderId);
	    if (order.isPresent()) {
	        Order o1 = order.get();
	        o1.setOrderStatus(orderStatus);
	        Order updatedOrder = orderRepository.save(o1);
	        return updatedOrder.getOrderDto();
	    } else {
	        throw new Exception("Order Id not found");
	    }
	}
}
