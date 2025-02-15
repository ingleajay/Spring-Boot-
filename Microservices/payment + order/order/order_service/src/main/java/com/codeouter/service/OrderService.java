package com.codeouter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codeouter.helper.TransactionRequest;
import com.codeouter.helper.TransactionResponse;
import com.codeouter.model.Order;
import com.codeouter.model.Payment;
import com.codeouter.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public TransactionResponse saveorder(TransactionRequest transactionRequest) {
		String message = "";
		
		Order order = transactionRequest.getOrder();
		Payment payment = transactionRequest.getPayment();
		payment.setOrderid(order.getId());
		payment.setAmount(order.getPrice());
		
		// rest call
		
		Payment  paymentResponse = restTemplate.postForObject("http://payment-service/payment/dopayment", payment, Payment.class);
		
		message = paymentResponse.getPaymentStatus().equals("success")? "payment proccessing successful and order placed":"there is fauilare in paymenet api ";
		orderRepository.save(order);
		return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),message);
	}
}
