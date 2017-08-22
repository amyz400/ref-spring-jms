package jms.service;

import java.util.Optional;

import jms.model.Order;

public interface StoreService {
	
	void registerOrder(Order order);
	
	Optional<Order> getReceivedOrder(String id);
}
