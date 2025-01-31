package com.finance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.finance.dto.UserDTO;

@Entity
@Table(name = "expenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Category cannot be null")
    @Column(nullable = false)
    private String category;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount should be positive")
    @Column(nullable = false)
    private Double amount;

    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message="Date should be in present or future")
    @Column(nullable = false)
    private LocalDate date;
    
    @Column
    private String description;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;
}
