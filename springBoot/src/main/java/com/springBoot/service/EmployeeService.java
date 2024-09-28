package com.springBoot.service;


import com.springBoot.DTO.EmployeeUpdateDTO;
import com.springBoot.enums.StatusOfEmployee;
import com.springBoot.model.Employee;
import com.springBoot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() { return employeeRepository.findAll(); }

    public Optional<Employee> getEmployeeById(Long id) { return employeeRepository.findById(id); }

    public List<Employee> getEmployeesByStatus(StatusOfEmployee status){
        return employeeRepository.findByStatus(status);
    }

    public List<Employee> getEmployeesByDepartment(String department){
        return employeeRepository.findByDepartment(department);
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployeeStatus(Long id, EmployeeUpdateDTO employeeUpdateDTO){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if(optionalEmployee.isPresent() && employeeUpdateDTO.getStatus() != null && employeeUpdateDTO.getDepartment() != null){
            Employee employee = optionalEmployee.get();
            employee.setStatus(employeeUpdateDTO.getStatus());
            employee.setDepartment(employeeUpdateDTO.getDepartment());
            return Optional.of(employeeRepository.save(employee));
        }
        else if(optionalEmployee.isPresent() && employeeUpdateDTO.getStatus() != null){
            Employee employee = optionalEmployee.get();
            employee.setStatus(employeeUpdateDTO.getStatus());
            return Optional.of(employeeRepository.save(employee));
        }
        else if(optionalEmployee.isPresent() && employeeUpdateDTO.getDepartment() != null){
            Employee employee = optionalEmployee.get();
            employee.setDepartment(employeeUpdateDTO.getDepartment());
            return Optional.of(employeeRepository.save(employee));
        }
        return Optional.empty();
    }
}
