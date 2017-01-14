package com.kowalik.dominik.dao;

import com.kowalik.dominik.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dominik on 2016-12-26.
 */
@Repository("employeeRepository")
public interface  EmployeeRepository extends CrudRepository<Employee,Integer> {
    Employee findByFirstnameAndPesel(String firstname, String pesel);
    Integer deleteByFirstnameAndPesel(String firstname, String pesel);
    Employee findByPesel(String pesel);
}
