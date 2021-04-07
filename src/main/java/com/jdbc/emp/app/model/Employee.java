package com.jdbc.emp.app.model;

public class Employee {

	private Long id;
	private String name;
	private Long salary;
	private String role;
	private String email;

	public Employee() {
	}

	public Employee(Long id, String name, Long salary, String role, String email) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.role = role;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", role=" + role + ", email=" + email
				+ "]";
	}

}
