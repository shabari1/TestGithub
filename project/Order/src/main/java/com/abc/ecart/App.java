package com.abc.ecart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

import com.abc.ecart.services.OrderService;
@SpringBootApplication
@EnableIntegration
@ImportResource({"classpath:applicationContext.xml"})
public class App  implements CommandLineRunner{

	@Autowired
	OrderService orderService;
	
	@Override
	public void run(String... args){
		orderService.placeOrder("Nike", 55, "01234567890");
		System.out.println("\n second order.......\n");
		orderService.placeOrder("Nike", 5, "01234567890");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
