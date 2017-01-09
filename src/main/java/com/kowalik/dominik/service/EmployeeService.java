package com.kowalik.dominik.service;

import com.kowalik.dominik.dao.EmployeeRepository;
import com.kowalik.dominik.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by dominik on 2017-01-08.
 */
@Service("emplyeeService")
public class EmployeeService {

    @Autowired
    @Qualifier("employeeHashSet")
    HashSet<Employee> employees;

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeById(int id) {
        return employeeRepository.findOne(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public HashSet<Employee> getEmployees() {
        if (!employees.isEmpty()) {
            employees.clear();
        }
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        employeeIterable.forEach(e -> employees.add(e));
        return employees;
    }
}
