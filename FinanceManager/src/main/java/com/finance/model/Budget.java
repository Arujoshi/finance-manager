package com.finance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "budgets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Category cannot be null")
    @Column(nullable = false)
    private String category;

    @NotNull(message = "Budget amount cannot be null")
    @Positive(message="Budget amount must be positive")
    @Column(nullable = false)
    private Double budgetAmount;

    @Column(nullable = false)
    private Double spentAmount = 0.0; // Track expenses against the budget

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user; // Link budget to a specific user
}
