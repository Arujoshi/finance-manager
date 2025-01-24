package com.finance.mappper;

import com.finance.dto.IncomeDTO;
import com.finance.model.Income;

public class IncomeMapper {

    // Convert Income to IncomeDTO
    public static IncomeDTO toDTO(Income income) {
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setId(income.getId());
        incomeDTO.setSource(income.getSource());
        incomeDTO.setAmount(income.getAmount());
        incomeDTO.setDate(income.getDate());
        incomeDTO.setDescription(income.getDescription());

        // Map user data
        if (income.getUser() != null) {
            incomeDTO.setUser(UserInfoMapper.toDTO(income.getUser()));  // Map UserInfo to UserDTO
        }

        return incomeDTO;
    }
}
