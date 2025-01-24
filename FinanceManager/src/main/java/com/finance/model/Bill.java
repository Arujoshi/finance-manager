package com.finance.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Bill name should not be empty")
    @Column
    private String name;  // Name of the bill (e.g., "Electricity Bill")
    
    @NotNull(message="Bill amount should not be empty")
    @Positive(message="Bill amount should be positive")
    @Column
    private Double amount;  // Amount for the bill
   
    @NotNull(message="Bill due date should not be empty")
    @FutureOrPresent(message="Due date should be in future or present")
    @Column
    private LocalDate dueDate; 
    
    @Column
    private String description;// Due date for the bill

    @NotNull(message="User should not be empty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;  // The user to whom the bill belongs

}
