package com.abc.ecart.services;

import org.springframework.stereotype.Component;



@Component("emailService")
public class EmailService {
	
	
	public boolean sendEmail(String toAddress, String subject, String emilBody){
		System.out.println("Email successfully sent to :" + toAddress);
		return true;
	}	

}
