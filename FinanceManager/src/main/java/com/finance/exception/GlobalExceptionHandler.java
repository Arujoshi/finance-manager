package com.finance.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(AccountLockedException.class) // Custom Exception if needed
    public ResponseEntity<String> handleAccountLockedException(AccountLockedException ex) {
        return ResponseEntity.status(HttpStatus.LOCKED)
                             .body("Your account is locked. Please contact support.");
    }
    
    @ExceptionHandler(IncomeNotFoundException.class) // Custom Exception if needed
    public ResponseEntity<String> handleIncomeNotFoundException(IncomeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ex.getMessage());
    }
    
    @ExceptionHandler(ExpenseNotFoundException.class) // Custom Exception if needed
    public ResponseEntity<String> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ex.getMessage());
    }
    
    @ExceptionHandler(BudgetNotFoundException.class) // Custom Exception if needed
    public ResponseEntity<String> handleBudgetNotFoundException(BudgetNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ex.getMessage());
    }
    
    @ExceptionHandler(BillNotFoundException.class) // Custom Exception if needed
    public ResponseEntity<String> handleBillNotFoundException(BillNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
