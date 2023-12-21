package com.selivanov.springweb.controller;

import com.selivanov.springweb.entity.Employee;
import com.selivanov.springweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee/employees";
    }

    @GetMapping("/employees/new")
    public String createNewEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/new_employee";
    }

    @PostMapping("/employees")
    public String createNewEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/remove")
    public String deleteEmployee() {
        return "employee/delete_employee";
    }

    @PostMapping("/employees/remove")
    public String deleteEmployee(@RequestParam Integer id) {
        if (id != null) {
            employeeService.deleteEmployee(id);
        }
        return "redirect:/employees";
    }

    @GetMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable Integer id, Model model) {
        if (id != null) {
            Employee employee = employeeService.getEmployeeById(id);
            model.addAttribute("employee", employee);
        }
        return "employee/update_employee";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee, employee.getId());
        return "redirect:/employees";
    }

}
