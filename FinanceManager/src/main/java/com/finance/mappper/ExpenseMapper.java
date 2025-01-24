package com.finance.mappper;

import com.finance.dto.ExpenseDTO;
import com.finance.model.Expense;

public class ExpenseMapper {


    // Convert Income to IncomeDTO
    public static ExpenseDTO toDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setCategory(expense.getCategory());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setDate(expense.getDate());
        expenseDTO.setDescription(expense.getDescription());

        // Map user data
        if (expense.getUser() != null) {
        	expenseDTO.setUser(UserInfoMapper.toDTO(expense.getUser()));  // Map UserInfo to UserDTO
        }

        return expenseDTO;
    }

}
