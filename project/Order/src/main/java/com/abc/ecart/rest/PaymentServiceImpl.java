package com.abc.ecart.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.springframework.stereotype.Component;

@Component("paymentService")
public class PaymentServiceImpl implements PaymentService{

	String myResult = "";
	private static final String url = "http://localhsot:8080/payment/1";
	private static final String methodType= "GET";
	
	@Mock
	HttpURLConnection mockHttpURLConnection;
	
	
	public boolean chargePayment(String cardNumber, double amount){		
		String result = null;
		boolean paymentStatus = false;
		URL serviceUrl = null;
		HttpURLConnection conn = null;		
		mockHttpURLConnection = EasyMock.createMock(HttpURLConnection.class);
		
		try {
			
			InputStream in = new ByteArrayInputStream("true".getBytes(StandardCharsets.UTF_8.name()));
			EasyMock.expect(mockHttpURLConnection.getResponseCode()).andReturn(200).anyTimes();			
			EasyMock.expect(mockHttpURLConnection.getInputStream()).andReturn(in).anyTimes();
			EasyMock.replay(mockHttpURLConnection);
			/**
			serviceUrl = new URL(url);
			conn = (HttpURLConnection) serviceUrl.openConnection();
			conn.setRequestMethod(methodType);
			conn.setRequestProperty("Accept", "application/json");
			*/
						
			System.out.println("connection....:" + mockHttpURLConnection.getResponseCode());
			
			if (mockHttpURLConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + mockHttpURLConnection.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((mockHttpURLConnection.getInputStream())));
			result = br.readLine();
			System.out.println("Output from Server ....:" + result);
			paymentStatus = Boolean.getBoolean(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			/**conn.disconnect();*/
		}
		return paymentStatus;
	}
}
