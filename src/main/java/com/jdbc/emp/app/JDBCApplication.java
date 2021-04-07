package com.jdbc.emp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbc.emp.app.client.JdbcClient;

@SpringBootApplication
public class JDBCApplication implements CommandLineRunner {

	@Autowired
	JdbcClient jdbcClient;

	public static void main(String[] args) {
		SpringApplication.run(JDBCApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		jdbcClient.performDBOperations();

	}

}
