package jms;


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
import jms.service.StoreService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleListenerTest {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private StoreService storeService;
	
	@Test
	public void sendSimpleMessage() throws InterruptedException {
		clientService.addOrder(new Order("order1"));
		Thread.sleep(500);
		
		Optional<Order> storedOrder = storeService.getReceivedOrder("order1");
		Assert.assertTrue(storedOrder.isPresent());
		Assert.assertEquals("order1", storedOrder.get().getId());
	}
}
