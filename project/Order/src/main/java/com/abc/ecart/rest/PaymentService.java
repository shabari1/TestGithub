package com.abc.ecart.rest;

public interface PaymentService {	
	public boolean chargePayment(String cardNumber, double amount );
}
