package com.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

	 	private Long id;
	    private String category;
	    private Double budgetAmount;
	    private Double spentAmount; // To display remaining budget
	    private UserDTO user;  
}
