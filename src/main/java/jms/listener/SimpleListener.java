package jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jms.model.Order;
import jms.service.StoreService;

@Component
public class SimpleListener {
	private final StoreService storeService;
	
	@Autowired
	public SimpleListener(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * Just listen and process data that is received.  Do not pass it on to another queue
	 * @param order
	 */
	@JmsListener(destination = "simple.queue")
	public void receiveOrder(Order order) {
		storeService.registerOrder(order);
	}
}
