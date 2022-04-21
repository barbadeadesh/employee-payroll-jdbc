package com.bridgelabz.payroll_service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/* @purpose : Create class for connect the database
* 
*  @author : Adesh Barbade
* 
*  @since :
* 
*/

public class DBConnection {
	public static void main(String[] args) {
		String dbURL = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "Adesh@12345";
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath", e);
		}
		listDrivers();
		try {
			System.out.println("connecting to the database:" + dbURL);
			connection = DriverManager.getConnection(dbURL, username, password);
			System.out.println("connection is successful!!!!" + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(driverClass.getClass().getName());
		}
	}
}
