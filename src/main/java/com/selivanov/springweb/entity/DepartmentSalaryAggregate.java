package com.selivanov.springweb.entity;

import java.math.BigDecimal;

public class DepartmentSalaryAggregate {
    private String department;
    private BigDecimal salary;

    public DepartmentSalaryAggregate(String department, BigDecimal salary) {
        this.department = department;
        this.salary = salary;
    }

    public DepartmentSalaryAggregate() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
