package com.codeouter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeouter.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderid(int orderid);

}
