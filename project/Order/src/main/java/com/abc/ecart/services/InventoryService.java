package com.abc.ecart.services;

import org.springframework.stereotype.Component;

@Component
public class InventoryService {
	
	int availableQuantity = 15;
	
	public boolean checkInventory(String productId, int quantity){		
		boolean availability = true;
		if(null!= productId && availableQuantity < quantity){
			System.out.println("Order quantity is more than inventory");
			availability = false;
		}
		return availability;
	}
}
