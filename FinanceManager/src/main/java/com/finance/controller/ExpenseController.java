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

import com.finance.exception.ExpenseNotFoundException;
import com.finance.model.Expense;
import com.finance.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
	
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getExpensesByUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(expenseService.getExpensesByUserId(userId),HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody @Valid Expense expense) {
        return new ResponseEntity<>(expenseService.addExpense(expense), HttpStatus.OK);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable("expenseId") Long expenseId) throws ExpenseNotFoundException {
        return new ResponseEntity<>(expenseService.deleteExpense(expenseId),HttpStatus.OK);
    }
    @PutMapping("/{expenseId}")
    public ResponseEntity<?> updateExpense(@PathVariable("expenseId") Long expenseId,@RequestBody @Valid Expense expense ) throws ExpenseNotFoundException {
        return new ResponseEntity<>(expenseService.updateExpense(expenseId,expense),HttpStatus.OK);
    }
}
