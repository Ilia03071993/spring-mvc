package com.selivanov.springweb.service;

import com.selivanov.springweb.entity.Employee;
import com.selivanov.springweb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getEmployeeById(id).
                orElseThrow(() -> new NoSuchElementException(
                        "Employee with id = '%d' not found".formatted(id)));
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.saveEmployee(employee);
    }

    public void updateEmployee(Employee employee, Integer id) {
        employeeRepository.updateEmployee(employee, id);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteEmployeeById(id);
    }

//    public void sortEmployeeByDepartmentWithAvrSalary(@RequestParam(required = false) String sort) {
//        if (sort.equalsIgnoreCase("max")){
//            getAllEmployees().
//        }
//    }
}