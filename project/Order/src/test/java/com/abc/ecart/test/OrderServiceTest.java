package com.abc.ecart.test;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.abc.ecart.rest.PaymentService;
import com.abc.ecart.services.EmailService;
import com.abc.ecart.services.InventoryService;
import com.abc.ecart.services.OrderService;


@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrderServiceTest {
	
	@TestSubject
	OrderService orderService;
	
	@Mock
	InventoryService mockInventoryService;
	@Mock
	PaymentService mockPaymentService;
	@Mock
	EmailService mockEmailService; 
	
	@Before
	public void setUp() throws Exception {
		orderService = new OrderService();		
		
		mockPaymentService = EasyMock.createMock(PaymentService.class);
		mockInventoryService = EasyMock.createMock(InventoryService.class);
		mockEmailService = EasyMock.createMock(EmailService.class);
		ReflectionTestUtils.setField(orderService, "paymentService", mockPaymentService);
		ReflectionTestUtils.setField(orderService, "inventoryService", mockInventoryService);
		ReflectionTestUtils.setField(orderService, "emailService", mockEmailService);
	}

	@Test
	public final void testDoPayment() {
		EasyMock.expect(mockPaymentService.chargePayment("1234567890", 0.25)).andReturn(true).anyTimes();
		EasyMock.replay(mockPaymentService);
		
		boolean status = mockPaymentService.chargePayment("1234567890", 0.25);
		assertTrue(status);
	}
	
	@Test
	public final void testPlaceOrder() {
		
		String productId = "Nike";
		int quantity = 5;
		String cardNumber = "01234567890";
		
		EasyMock.expect(mockInventoryService.checkInventory(productId, quantity)).andReturn(true).anyTimes();
		EasyMock.replay(mockInventoryService);
		
		EasyMock.expect(mockPaymentService.chargePayment(cardNumber, 0.2)).andReturn(true).anyTimes();
		EasyMock.replay(mockPaymentService);
		
		EasyMock.expect(mockEmailService.sendEmail("abc@gmail.com", "Order details", "Your order successfully Placed.")).andReturn(true).anyTimes();
		EasyMock.replay(mockEmailService);
		
		
		
		boolean status = orderService.placeOrder(productId, quantity, cardNumber);
		System.out.println("status.......:" + status);
		assertTrue(status);
	}	
}
