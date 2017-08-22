package jms;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jms.model.Order;
import jms.service.ClientService;
import jms.service.RegisterService;
import jms.service.StoreService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InOutListenerTest {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private RegisterService registerService;
	
	@Test
	public void registerOrder() throws InterruptedException {
		String orderId = "someOrderId";
		clientService.registerOrder(new Order(orderId));
		Thread.sleep(1000);

		// confirm that the InListener received the order and saved it
		assertNotNull(storeService.getReceivedOrder(orderId));
		// assert that the OutListener also received the order and saved it
		assertTrue(registerService.getLastReceivedOrderId().compareTo(orderId) == 0);

	}
}
