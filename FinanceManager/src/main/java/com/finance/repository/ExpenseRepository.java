package com.finance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByUserUserId(Long userId);
	List<Expense> findByUserUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
	List<Expense> findByUserUserIdAndCategory(Long userId, String category);
}
