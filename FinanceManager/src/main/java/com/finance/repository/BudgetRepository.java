package com.finance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
	List<Budget> findByUserUserId(Long userId);
	Optional<Budget> findByUserUserIdAndCategory(Long userId, String category);
}
