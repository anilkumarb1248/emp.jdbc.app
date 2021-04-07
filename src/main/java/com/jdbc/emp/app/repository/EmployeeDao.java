package com.jdbc.emp.app.repository;

import com.jdbc.emp.app.model.Employee;

public interface EmployeeDao {

	public Employee getEmployee(Long id);

	public boolean addEmployee(Employee employee);

	public boolean deleteEmployee(Long id);

	public boolean updateEmployee(Long id, Employee employee);

}
