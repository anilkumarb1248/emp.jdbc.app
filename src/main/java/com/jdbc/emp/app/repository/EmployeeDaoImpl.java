package com.jdbc.emp.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.jdbc.emp.app.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/empdb";
	static final String USERNAME = "anil";
	static final String PASSWORD = "anil";
	
	// Not working : Access denied for user 'anilb'@'localhost
//	@Value("${drivername}")
//	String DRIVER_NAME;
//	
//	@Value("${url}")
//	String URL;
//	
//	@Value("${username}")
//	String USERNAME;
//	
//	@Value("${password}")
//	String PASSWORD;

	@Override
	public Employee getEmployee(Long id) {

		Employee employee = new Employee();

		Connection conn = null;
		Statement stmt = null;
		// Need to handle the exceptions using try-catch
		try {
			// Step 1: Register JDBC driver
			Class.forName(DRIVER_NAME);

			// Step 2: Create a connection
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// Step 3: Create a statement
			stmt = conn.createStatement();

			// Step 4: Execute the query
			String query = "select * from employee where id=" + id;
			ResultSet rs = stmt.executeQuery(query);

			// Step 5: Extract the data from result set
			while (rs.next()) {
				employee.setId(rs.getLong("ID"));
				employee.setName(rs.getString("NAME"));
				employee.setSalary(rs.getLong("SALARY"));
				employee.setRole(rs.getString("ROLE"));
				employee.setEmail(rs.getString("EMAIL"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Step 6: Close the connection and statement
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return employee;
	}

	@Override
	public boolean addEmployee(Employee employee) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "insert into employee(id,name,salary,role,email) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setLong(1, employee.getId());
			pstmt.setString(2, employee.getName());
			pstmt.setLong(3, employee.getSalary());
			pstmt.setString(4, employee.getRole());
			pstmt.setString(5, employee.getEmail());

			int i = pstmt.executeUpdate();
			System.out.println("Added records: " + i);

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return true;
	}

	@Override
	public boolean deleteEmployee(Long id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query = "delete from employee where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();
			System.out.println("deleted the record: " + i);

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return true;
	}

	@Override
	public boolean updateEmployee(Long id, Employee employee) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "update employee set name=?,salary=?,role=?,email=? where id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, employee.getName());
			pstmt.setLong(2, employee.getSalary());
			pstmt.setString(3, employee.getRole());
			pstmt.setString(4, employee.getEmail());
			pstmt.setLong(5, employee.getId());

			int i = pstmt.executeUpdate();
			System.out.println("Updated records: " + i);

			conn.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return true;
	}

}
