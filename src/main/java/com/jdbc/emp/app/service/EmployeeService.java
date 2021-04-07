package com.jdbc.emp.app.service;

import com.jdbc.emp.app.model.Employee;

public interface EmployeeService {

	public boolean addEmployee(Employee employee);

	public Employee getEmployee(Long id);

	public boolean deleteEmployee(Long id);

	public boolean updateEmployee(Long id, Employee employee);

}
