package com.finance.mappper;

import com.finance.dto.BudgetDTO;
import com.finance.model.Budget;

public class BudgetMapper {

	public static BudgetDTO toDTO(Budget budget) {
		BudgetDTO budgetDTO = new BudgetDTO();
		budgetDTO.setId(budget.getId());
		budgetDTO.setCategory(budget.getCategory());
		budgetDTO.setBudgetAmount(budget.getBudgetAmount());
		budgetDTO.setSpentAmount(budget.getSpentAmount());

		// Map user data
		if (budget.getUser() != null) {
			budgetDTO.setUser(UserInfoMapper.toDTO(budget.getUser())); // Map UserInfo to UserDTO
		}

		return budgetDTO;
	}
}