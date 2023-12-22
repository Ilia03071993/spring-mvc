package com.selivanov.springweb.controller;

import com.selivanov.springweb.entity.DepartmentSalaryAggregate;
import com.selivanov.springweb.entity.Employee;
import com.selivanov.springweb.model.Aggregation;
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

    //    @GetMapping("/employees/remove")
//    public String deleteEmployee() {
//        return "employee/delete_employee";
//    }
    @GetMapping("/employees/remove/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId, Model model) {
//            Employee deleteEmployee = employeeService.getEmployeeById(employeeId);
//
//            model.addAttribute("employee", deleteEmployee);
        if (employeeId != null) {
            employeeService.deleteEmployee(employeeId);
        }
        return "employee/employees";
    }

//    @PostMapping("/employees/remove")
//    public String deleteEmployee(@RequestParam Integer id) {
//        if (id != null) {
//            employeeService.deleteEmployee(id);
//        }
//        return "redirect:/employees";
//    }

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
        Integer id = employee.getId();
        employeeService.updateEmployee(employee, id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/aggregate_form")
    public String aggregateByDepartment() {
        return "employee/aggregate_form";
    }

    @GetMapping("/employees/aggregate_employees")
    public String aggregateByDepartment(@RequestParam Aggregation aggregation, Model model) {
        List<Employee> employees = employeeService.aggregateByDepartment(aggregation);
        model.addAttribute("employees", employees);
        return "employee/aggregate_employees";
    }
}
