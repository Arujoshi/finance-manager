package com.finance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finance.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
	@Query("SELECT b FROM Bill b WHERE b.dueDate BETWEEN CURRENT_DATE AND :dueDate")
    List<Bill> findBillsDueInNextDays(@Param("dueDate") LocalDate dueDate);
	
	List<Bill> findByUserUserId(Long userId);
}
