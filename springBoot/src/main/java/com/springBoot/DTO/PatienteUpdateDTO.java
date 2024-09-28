package com.springBoot.DTO;


import com.springBoot.model.Employee;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatienteUpdateDTO {

    private String name;
    private LocalDate dateOfBirth;
    private Employee employeeId;
}
