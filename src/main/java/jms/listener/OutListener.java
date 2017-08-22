package jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jms.service.RegisterService;

@Component
public class OutListener {
	private final RegisterService registerService;
	
	@Autowired
	public OutListener(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@JmsListener(destination = "out.queue")
	public void receiveOrder(String orderId) {
		// process the data that received on the queue
		registerService.registerOrderId(orderId);
	}

}
