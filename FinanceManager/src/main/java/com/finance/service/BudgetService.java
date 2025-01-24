package com.finance.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finance.dto.BudgetDTO;
import com.finance.exception.BudgetNotFoundException;
import com.finance.exception.ExpenseNotFoundException;
import com.finance.mappper.BudgetMapper;
import com.finance.model.Budget;
import com.finance.model.Expense;
import com.finance.model.UserInfo;
import com.finance.repository.BudgetRepository;
import com.finance.repository.ExpenseRepository;
import com.finance.repository.UserInfoRepository;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserInfoRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetService(BudgetRepository budgetRepository, UserInfoRepository userRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public BudgetDTO addBudget(Budget budget) {
        UserInfo user = userRepository.findById(budget.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        Optional<Budget> existingBudgetOpt = budgetRepository.findByUserUserIdAndCategory(budget.getUser().getUserId(), budget.getCategory());
        Budget gotBudget;
        
        if (existingBudgetOpt.isPresent()) {
            // Update the amount of the existing budget
            gotBudget = existingBudgetOpt.get();
            System.out.print(gotBudget);
            gotBudget.setBudgetAmount(budget.getBudgetAmount());;
        } else {
            // Create a new budget
        	gotBudget=new Budget();
        	gotBudget.setCategory(budget.getCategory());
        	gotBudget.setBudgetAmount(budget.getBudgetAmount());
        	gotBudget.setUser(user);
        }
        return BudgetMapper.toDTO(budgetRepository.save(gotBudget));
    }

    public List<BudgetDTO> getBudgetsByUserId(Long userId) {
        return budgetRepository.findByUserUserId(userId)
                .stream()
                .map(BudgetMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BudgetDTO updateBudget(Long budgetId, Budget budget) throws BudgetNotFoundException {
        Budget foundBudget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException("Budget not found with id "+budgetId));

        foundBudget.setCategory(budget.getCategory());
        foundBudget.setBudgetAmount(budget.getBudgetAmount());
        foundBudget.setSpentAmount(budget.getSpentAmount());
        
        return BudgetMapper.toDTO(budgetRepository.save(foundBudget));
    }

    public BudgetDTO deleteBudget(Long budgetId) throws BudgetNotFoundException{
    	Budget budget=budgetRepository.findById(budgetId).orElseThrow(()->new BudgetNotFoundException("Budget not found with id "+budgetId));
        budgetRepository.deleteById(budgetId);
        return BudgetMapper.toDTO(budget);
    }

    public List<BudgetDTO> getBudgetSummaryByUserId(Long userId) {
        // Fetch all budgets for the user
        List<Budget> budgets = budgetRepository.findByUserUserId(userId);

        // Fetch all expenses for the user grouped by category
        Map<String, Double> spentAmountsByCategory = expenseRepository.findByUserUserId(userId)
                .stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory, 
                        Collectors.summingDouble(Expense::getAmount)
                ));
        
        System.out.println(spentAmountsByCategory);

        // Map budgets to DTOs with spent amount
        return budgets.stream()
                .map(budget -> {
                    double spentAmount = spentAmountsByCategory.getOrDefault(budget.getCategory(), 0.0);
                    BudgetDTO budgetDTO = BudgetMapper.toDTO(budget);
                    budgetDTO.setSpentAmount(spentAmount);
                    return budgetDTO;
                })
                .collect(Collectors.toList());
    }
}
