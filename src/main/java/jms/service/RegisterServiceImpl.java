package jms.service;

import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
	private String lastReceivedOrder;

	/**
	 * This method will save the last order that was received
	 * @param orderId
	 */
	public void registerOrderId(String orderId) {

		this.lastReceivedOrder = orderId;
	}

	public String getLastReceivedOrderId() {

		return this.lastReceivedOrder;
	}
}
