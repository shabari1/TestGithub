package com.abc.ecart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.ecart.rest.PaymentService;

@Component
public class OrderService {

	@Autowired
	InventoryService inventoryService;
	@Autowired
	EmailService emailService;	
	@Autowired
	PaymentService paymentService;
	
	public boolean placeOrder(String productId, int quantity, String cardNumber){
		boolean isValidInventory = false;
		boolean orderStatus = false;
		double charges = 0.2d;
		
		isValidInventory = processorOrder(productId, quantity);		
		System.out.println("Order Placed status ..:" + isValidInventory);
		
		if(isValidInventory){
			System.out.println("Checking payment process");
			orderStatus = paymentService.chargePayment(cardNumber, charges);
			System.out.println("Order payment processed Successfully, your order will be shipped in 2 working days.");
		}
		
		if(orderStatus){
			emailService.sendEmail("abc@gmail.com", "Order details", "Your order successfully Placed.");
			System.out.println("Email sent successfully...");
		}		
		return orderStatus;
		
	}
	
	public boolean processorOrder(String productId, int quantity){
		boolean isValidInventory = false;
		if(null == productId || productId.isEmpty()){
			System.out.println("Invalid Product");
		} else if( quantity <= 0){
			System.out.println("Please enter valid Product quantity");
		} else {
			isValidInventory = checkInventory(productId, quantity);
		}
		
		return isValidInventory;
		
	}
	
	public boolean checkInventory(String productId, int quantity){
		return inventoryService.checkInventory(productId, quantity);
	}	
}
