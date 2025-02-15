package com.codeouter.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeouter.model.Payment;
import com.codeouter.repository.PaymentRepository;

@Service
public class PaymnetService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentprocessing());
	payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepository.save(payment);
	}
	
	public String paymentprocessing() {
		return new Random().nextBoolean()?"success":"false";
	}
	
	public Payment findpaymentHistoryByOrderId(int orderid) {
		return paymentRepository.findByOrderid(orderid);
	}
}
