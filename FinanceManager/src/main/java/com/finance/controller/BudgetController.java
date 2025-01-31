package com.finance.controller;

import java.util.List;

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

import com.finance.exception.BudgetNotFoundException;
import com.finance.model.Budget;
import com.finance.service.BudgetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<?> addBudget(@RequestBody @Valid Budget budget) {
    	return new ResponseEntity<>(budgetService.addBudget(budget),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBudgetsByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(budgetService.getBudgetsByUserId(userId),HttpStatus.OK);
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<?> updateBudget(@PathVariable("budgetId") Long budgetId, @RequestBody @Valid Budget budget) throws BudgetNotFoundException {
        return new ResponseEntity<>(budgetService.updateBudget(budgetId, budget), HttpStatus.OK);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<?> deleteBudget(@PathVariable("budgetId") Long budgetId) throws BudgetNotFoundException {
       return new ResponseEntity<>(budgetService.deleteBudget(budgetId),HttpStatus.OK);
    }
    
    @GetMapping("/{userId}/summary")
    public ResponseEntity<?> getBudgetSummaryByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(budgetService.getBudgetSummaryByUserId(userId),HttpStatus.OK);
    }

}
