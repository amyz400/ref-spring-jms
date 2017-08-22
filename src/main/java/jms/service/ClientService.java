package jms.service;

import jms.model.Order;

public interface ClientService {
	
	void addOrder(Order order);
	
	void registerOrder(Order order);
}
