package jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jms.model.Order;

@Service
public class ClientServiceImpl implements ClientService {
	private static final String SIMPLE_QUEUE = "simple.queue";
	private static final String IN_QUEUE = "in.queue";
	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public ClientServiceImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void addOrder(Order order) {
		jmsTemplate.convertAndSend(SIMPLE_QUEUE, order);
	}

	/**
	 * This class represents the processing of data received in a client app.  The data is
	 * provided in this method.  Any manipulation, storage or use of the data can happen, but the
	 * data is ultimately passed on to a queue for processing by a different application.  In this
	 * example both the client and the server are represented in the same application.
	 * @param order
	 */
	public void registerOrder(Order order) {
		// send the InListener
		jmsTemplate.convertAndSend(IN_QUEUE, order);
	}
}
