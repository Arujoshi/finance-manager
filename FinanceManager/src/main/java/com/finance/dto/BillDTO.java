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
public class BillDTO {
    private Long id;

    private String name;  // Name of the bill (e.g., "Electricity Bill")
 
    private Double amount;  // Amount for the bill

    private LocalDate dueDate; 
    
    private String description;// Due date for the bill

    private UserDTO user;  // The user to whom the bill belongs

}
