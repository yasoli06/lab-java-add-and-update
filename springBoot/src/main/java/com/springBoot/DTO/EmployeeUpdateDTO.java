package com.springBoot.DTO;


import com.springBoot.enums.StatusOfEmployee;
import lombok.Data;

@Data
public class EmployeeUpdateDTO {
    private StatusOfEmployee status;
    private String department;
}
