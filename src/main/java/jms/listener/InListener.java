package jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import jms.model.Order;
import jms.service.StoreService;

@Component
public class InListener {
	private final StoreService storeService;
	
	@Autowired
	public InListener(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@JmsListener(destination = "in.queue")
	@SendTo("out.queue")
	public String receiveOrder(Order order) {
		// this queue has received an order; do something with it and then the it will be sent to the out.queue
		storeService.registerOrder(order);
		return order.getId();
	}
}
