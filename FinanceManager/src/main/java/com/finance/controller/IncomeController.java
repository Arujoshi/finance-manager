package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.exception.IncomeNotFoundException;
import com.finance.model.Income;
import com.finance.service.IncomeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    // Add Income for a specific user
    @PostMapping
    public ResponseEntity<?> addIncome(@RequestBody @Valid Income income) {
        return new ResponseEntity<>(incomeService.addIncome(income),HttpStatus.OK);
    }

    // Get all incomes for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<?> getIncomesByUserId(@PathVariable("userId") Long userId) {
    	return new ResponseEntity<>(incomeService.getIncomesByUserId(userId),HttpStatus.OK);
    }

    // Update income by ID
    @PutMapping("/{incomeId}")
    public ResponseEntity<?> updateIncome(@PathVariable("incomeId") Long incomeId, @RequestBody @Valid Income income) throws IncomeNotFoundException {
        return new ResponseEntity<>(incomeService.updateIncome(incomeId, income),HttpStatus.OK);
    }

    // Delete income by ID
    @DeleteMapping("/{incomeId}")
    public ResponseEntity<?> deleteIncome(@PathVariable("incomeId") Long incomeId) throws IncomeNotFoundException {
        return new ResponseEntity<>(incomeService.deleteIncome(incomeId),HttpStatus.OK);
    }
}
