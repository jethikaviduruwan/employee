package com.backendProject.employee.service;

import com.backendProject.employee.entity.Employee;
import com.backendProject.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee postEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with ID "+id+" not found");
        }
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id,Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee exsitingEmployee = optionalEmployee.get();

            exsitingEmployee.setEmail(employee.getEmail());
            exsitingEmployee.setName(employee.getName());
            exsitingEmployee.setDepartment(employee.getDepartment());
            exsitingEmployee.setPhone(employee.getPhone());

            return employeeRepository.save(exsitingEmployee);
        }
        return null;
    }
}
