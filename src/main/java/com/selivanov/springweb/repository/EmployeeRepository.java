package com.selivanov.springweb.repository;

import com.selivanov.springweb.entity.DepartmentSalaryAggregate;
import com.selivanov.springweb.entity.Employee;
import com.selivanov.springweb.model.Aggregation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public EmployeeRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Employee> getAllEmployees() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Employee> employees = entityManager
                    .createQuery("from Employee", Employee.class)
                    .getResultList();

            entityManager.getTransaction().commit();
            return employees;
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee employee = entityManager.createQuery(
                            """
                                    select e from Employee e
                                    where e.id = :id
                                    """, Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return Optional.ofNullable(employee);
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void saveEmployee(Employee employee) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            if (employee.getId() == null) {
                entityManager.persist(employee);
            }
//            else {
//                entityManager.merge(employee);
//            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void updateEmployee(Employee employee, Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee updateEmployee = entityManager.find(Employee.class, id);
            if (updateEmployee != null) {
                updateEmployee.setName(employee.getName());
                updateEmployee.setDepartment(employee.getDepartment());
                updateEmployee.setSalary(employee.getSalary());
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void deleteEmployeeById(Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee deleteEmployee = entityManager.find(Employee.class, id);
            if (deleteEmployee != null) {
                entityManager.remove(deleteEmployee);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Employee> aggregateByDepartment(Aggregation aggregationType) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Employee> salaryAggregates = switch (aggregationType) {
                case AVG -> entityManager.createQuery("""
                                select e.department, avg(e.salary) as avg_salary from Employee e
                                group by e.department
                                """, Employee.class)
                        .getResultList();
                case MIN -> entityManager.createQuery("""
                                select e.department, min(e.salary) min_salary from Employee e
                                group by e.department
                                """, Employee.class)
                        .getResultList();
                case MAX -> entityManager.createQuery("""
                                select e.department, max(e.salary) max_salary from Employee e
                                group by e.department
                                """, Employee.class)
                        .getResultList();
                case SUM -> entityManager.createQuery("""
                                select e.department, sum(e.salary) sum_salary from Employee e
                                group by e.department
                                """, Employee.class)
                        .getResultList();
            };

            entityManager.getTransaction().commit();
            return salaryAggregates;
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}