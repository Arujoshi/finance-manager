package com.finance.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {

	private Long id;
    private String source;
    private Double amount;
    private LocalDate date;
    private String description;
    private UserDTO user;
}
