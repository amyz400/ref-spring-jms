package jms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jms.model.Order;

@Service
public class StoreServiceImpl implements StoreService {
	private final List<Order> receivedOrders = new ArrayList<>();

	public void registerOrder(Order order) {
		this.receivedOrders.add(order);
	}
	
	public Optional<Order> getReceivedOrder(String id) {
		return receivedOrders.stream().filter(o -> o.getId().equals(id)).findFirst();
	}
}