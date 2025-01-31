package com.finance.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReportDTO {
    private double totalIncome;
    private double totalExpense;
    private double savings;
    private Map<String, Double> expenseBreakdown; // Category -> Amount
    private Map<String, Double> incomeBreakdown;  // Income Source -> Amount (if applicable)
    private Map<String, Double> budgetBreakdown;
    private List<String> trends; // List of trend observations (e.g., "Spending increasing on groceries")

}
