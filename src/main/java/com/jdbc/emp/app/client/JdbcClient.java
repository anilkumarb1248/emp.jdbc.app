package com.jdbc.emp.app.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdbc.emp.app.model.Employee;
import com.jdbc.emp.app.service.EmployeeService;

@Component
public class JdbcClient {
	
	@Autowired
	EmployeeService employeeService;

	private Scanner scanner = null;

	public void performDBOperations() {
		int choice = 0;
		scanner = new Scanner(System.in);

		while (choice != 5) {
			System.out.println("1: Add Employee\n" + "2: Get Employee\n" + "3: Delete Employee\n"
					+ "4: Update Employee\n" + "5: Exit");

			System.out.println("Enter your choice:\n");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				addEmployee();
				break;
			}
			case 2: {
				getEmployee();
				break;
			}
			case 3: {
				deleteEmployee();
				break;
			}
			case 4: {
				updateEmployee();
				break;
			}
			case 5: {
				scanner.close();
				scanner = null;
			}
			default: {
				System.out.println("Wrong Choice");
				break;
			}
			}
		}
	}

	private void addEmployee() {
		employeeService.addEmployee(getEmployeeDetails());
		System.out.println("Employee saved successfully");

	}

	private void getEmployee() {
		System.out.println("Enter the employee id:\n");
		Long id = scanner.nextLong();
		Employee employee = employeeService.getEmployee(id);
		System.out.println(employee);
	}

	private void deleteEmployee() {
		System.out.println("Enter the employee id to delete:\n");
		Long id = scanner.nextLong();
		boolean flag = employeeService.deleteEmployee(id);
		System.out.println("Employee is delete: " + flag);

	}

	private void updateEmployee() {
		Employee employee = getEmployeeDetails();
		employeeService.updateEmployee(employee.getId(), employee);
		System.out.println("Employee updated successfully");

	}

	private Employee getEmployeeDetails() {
		Employee employee = new Employee();

		System.out.println("Enter the employee id:\n");
		Long id = scanner.nextLong();

		System.out.println("Enter the employee name:\n");
		String name = scanner.next();

		System.out.println("Enter the employee salary:\n");
		Long salary = scanner.nextLong();

		System.out.println("Enter the employee role:\n");
		String role = scanner.next();

		System.out.println("Enter the employee email:\n");
		String email = scanner.next();

		employee.setId(id);
		employee.setName(name);
		employee.setSalary(salary);
		employee.setRole(role);
		employee.setEmail(email);
		return employee;
	}

	@Override
	protected void finalize() throws Throwable {
		if (scanner != null)
			scanner.close();
	}

}
