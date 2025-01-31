package com.finance.service;

import com.finance.dto.ExpenseDTO;
import com.finance.exception.ExpenseNotFoundException;
import com.finance.mappper.ExpenseMapper;
import com.finance.model.Budget;
import com.finance.model.Expense;
import com.finance.model.UserInfo;
import com.finance.repository.BudgetRepository;
import com.finance.repository.ExpenseRepository;
import com.finance.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserInfoRepository userRepository;

	public List<ExpenseDTO> getExpensesByUserId(Long userId) {
		List<Expense> expenses = expenseRepository.findByUserUserId(userId);

		return expenses.stream().map(ExpenseMapper::toDTO).collect(Collectors.toList());

	}

	public ExpenseDTO addExpense(Expense expense) {
		// Fetch the UserInfo by ID, do not expose sensitive data
		UserInfo userInfo = userRepository.findById(expense.getUser().getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
	// Set the UserInfo reference on the expense
		expense.setUser(userInfo);

		// Save the expense, including the reference to the UserInfo entity
		Expense savedExpense = expenseRepository.save(expense);
		checkAndNotifyBudgetExceedance(expense.getUser().getUserId());
		return ExpenseMapper.toDTO(savedExpense);
	}

	public ExpenseDTO deleteExpense(Long expenseId) throws ExpenseNotFoundException {
		// Find the expense by ID, or throw an exception if not found
		Expense expense = expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));

		// Delete the expense
		expenseRepository.deleteById(expenseId);

		// Return the ExpenseDTO (without sensitive user data)
		return ExpenseMapper.toDTO(expense);
	}

	public ExpenseDTO updateExpense(Long expenseId, Expense expense) throws ExpenseNotFoundException {
		// Find the existing expense by ID
		Optional<Expense> getExpense = expenseRepository.findById(expenseId);

		if (getExpense.isPresent()) {
			Expense existingExpense = getExpense.get();

			// Update the expense fields
			existingExpense.setAmount(expense.getAmount());
			existingExpense.setCategory(expense.getCategory());
			existingExpense.setDate(expense.getDate());
			existingExpense.setDescription(expense.getDescription());

			// Save the updated expense
			Expense updatedExpense = expenseRepository.save(existingExpense);

			// Convert UserInfo to UserDTO (so we don’t expose sensitive data)
			return ExpenseMapper.toDTO(updatedExpense);

		} else {
			throw new ExpenseNotFoundException("Expense not found with id: " + expenseId);
		}
	}

	public void checkAndNotifyBudgetExceedance(Long userId) {
            // Fetch all budgets for the user
            List<Budget> budgets = budgetRepository.findByUserUserId(userId);

            for (Budget budget : budgets) {
                // Calculate total expenses for this category
                Double totalExpenses = expenseRepository
                        .findByUserUserIdAndCategory(userId, budget.getCategory())
                        .stream()
                        .mapToDouble(Expense::getAmount)
                        .sum();

                // Check if expenses exceed the budget amount
                if (totalExpenses > budget.getBudgetAmount()) {
                    String message = "Alert: Your spending in the category '"
                            + budget.getCategory()
                            + "' has exceeded the budget of "
                            + budget.getBudgetAmount()
                            + ". Current spending: "
                            + totalExpenses;

                    // Send email notification
                    emailService.sendEmail(
                            budget.getUser().getEmail(),
                            "Budget Exceeded Alert",
                            message
                    );
                }
            }
	}

}
