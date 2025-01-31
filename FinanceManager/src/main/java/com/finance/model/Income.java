package com.finance.model;

import java.time.LocalDate;

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
@Table(name = "incomes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Source cannot be null")
    @Column(nullable = false)
    private String source;  // Source of income (e.g., salary, freelance, investments)

    @NotNull(message = "Amount cannot be null")
    @Positive(message="Amount should be positive")
    @Column(nullable = false)
    private Double amount;

    @NotNull(message = "Date cannot be null")
    @Column(nullable = false)
    private LocalDate date;  // Date of income

    @Column
    private String description;  // Optional description

    // Many-to-one relationship with UserInfo
    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // user_id is the foreign key column
    private UserInfo user;  // This links to the UserInfo entity
}
