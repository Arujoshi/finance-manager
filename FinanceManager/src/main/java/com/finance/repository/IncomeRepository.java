package com.finance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
	List<Income> findByUserUserId(Long userId);
	List<Income> findByUserUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
