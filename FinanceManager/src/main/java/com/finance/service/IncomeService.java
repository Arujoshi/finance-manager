package com.finance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dto.IncomeDTO;
import com.finance.exception.IncomeNotFoundException;
import com.finance.mappper.IncomeMapper;
import com.finance.model.Income;
import com.finance.model.UserInfo;
import com.finance.repository.IncomeRepository;
import com.finance.repository.UserInfoRepository;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserInfoRepository userRepository;  // To fetch UserInfo by userId

    // Create or add an income
    public IncomeDTO addIncome(Income income) {
        // Fetch the UserInfo by userId
        UserInfo user = userRepository.findById(income.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert DTO to entity and set the user
        income.setUser(user);

        // Save the income entity
        Income savedIncome = incomeRepository.save(income);

        // Return the saved income as DTO
        return IncomeMapper.toDTO(savedIncome);
    }

    // Get all incomes for a user
    public List<IncomeDTO> getIncomesByUserId(Long userId) {
        List<Income> incomes = incomeRepository.findByUserUserId(userId);
        return incomes.stream().map(IncomeMapper::toDTO).collect(Collectors.toList());
    }


    // Update an income
    public IncomeDTO updateIncome(Long incomeId, Income income) throws IncomeNotFoundException {
        Income foundIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income not found with id "+incomeId));

        foundIncome.setSource(income.getSource());
        foundIncome.setAmount(income.getAmount());
        foundIncome.setDate(income.getDate());
        foundIncome.setDescription(income.getDescription());

        // Save updated income
        Income updatedIncome = incomeRepository.save(foundIncome);

        return IncomeMapper.toDTO(updatedIncome);
    }

    // Delete an income
    public IncomeDTO deleteIncome(Long incomeId) throws IncomeNotFoundException {
        Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income not found with id "+incomeId));
        incomeRepository.delete(income);
        return IncomeMapper.toDTO(income);
    }
}
