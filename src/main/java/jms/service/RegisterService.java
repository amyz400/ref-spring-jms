package jms.service;

public interface RegisterService {
	void registerOrderId(String orderId);
	
	String getLastReceivedOrderId();
}
