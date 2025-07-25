package com.rachael.api.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rachael.api.wallet.constant.PaymentType;
import com.rachael.api.wallet.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query("SELECT p FROM Payment p WHERE YEAR(p.date) = :year AND p.type = :type")
    List<Payment> findByYearAndType(@Param("year") int year, @Param("type") PaymentType type);
	
}