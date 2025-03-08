package com.sakura.supermarket;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupermarketApplication {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		
		return connection;
	}
	
	public static void setConnection(Connection c) {
		
		connection = c;
	}

	public static void main(String[] args) {
		SpringApplication.run(SupermarketApplication.class, args);
		
		
	}

}


