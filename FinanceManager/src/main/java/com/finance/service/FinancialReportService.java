package com.finance.service;

import com.finance.dto.FinancialReportDTO;
import com.finance.model.Budget;
import com.finance.model.Expense;
import com.finance.model.Income;
import com.finance.repository.BudgetRepository;
import com.finance.repository.ExpenseRepository;
import com.finance.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinancialReportService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    

    public FinancialReportService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.budgetRepository=budgetRepository;
    }

    public FinancialReportDTO generateReport(Long userId, LocalDate startDate, LocalDate endDate) {
        FinancialReportDTO report = new FinancialReportDTO();

        // Fetch all incomes and expenses for the user within the period
        List<Income> incomes = incomeRepository.findByUserUserIdAndDateBetween(userId, startDate, endDate);
        List<Expense> expenses = expenseRepository.findByUserUserIdAndDateBetween(userId, startDate, endDate);
        List<Budget> budgets= budgetRepository.findByUserUserId(userId);
        // Total Income
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        report.setTotalIncome(totalIncome);

        // Total Expense
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        report.setTotalExpense(totalExpense);

        // Savings
        report.setSavings(totalIncome - totalExpense);

        // Expense Breakdown by Category
        Map<String, Double> expenseBreakdown = expenses.stream()
                .collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));
        report.setExpenseBreakdown(expenseBreakdown);

        // Income Breakdown by Source (if applicable)
        Map<String, Double> incomeBreakdown = incomes.stream()
                .collect(Collectors.groupingBy(Income::getSource, Collectors.summingDouble(Income::getAmount)));
        report.setIncomeBreakdown(incomeBreakdown);
        
        Map<String, Double> budgetBreakdown = budgets.stream()
                .collect(Collectors.groupingBy(Budget::getCategory, Collectors.summingDouble(Budget::getBudgetAmount)));
        report.setBudgetBreakdown(budgetBreakdown);

        // Trends (e.g., "Spending increasing on groceries")
        List<String> trends = identifyTrends(expenses, budgetBreakdown);
        report.setTrends(trends);

        return report;
    }
    
    private List<String> identifyTrends(List<Expense> expenses, Map<String, Double> categoryBudgets) {
        // Calculate total spending for each category
        Map<String, Double> categorySpend = expenses.stream()
                .collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)));

        List<String> trends = new ArrayList<>();

        // Compare spending with budgets
        categorySpend.forEach((category, amount) -> {
            Double budget = categoryBudgets.getOrDefault(category, 0.0); // Default budget to 0 if not found
            if (budget > 0 && amount >= 0.75 * budget) { // Spending is 75% or more of the budget
                trends.add("Spending is high in category: " + category + " (Spent: " + amount + ", Budget: " + budget + ")");
            }
        });
        return trends;
    }

}
