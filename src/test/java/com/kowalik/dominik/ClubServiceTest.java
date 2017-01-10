package com.kowalik.dominik;

import com.kowalik.dominik.model.Employee;
import com.kowalik.dominik.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClubServiceTest {

	@Autowired
	EmployeeService employeeService;

	@Test
	public void findAllTrainers() {
		Set<Employee> employees = employeeService.getEmployees();
		System.out.println(employees);
		Assert.assertNotNull(employees);
	}
}
