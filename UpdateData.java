package com.bridgelab.payroll_service;

import java.util.List;

/*@purpose : Create class for performing different function in payroll service related with database
 * 
 * @author : Adesh Barbade
 * 
 * @since :
 * 
 */

public class EmployeePayrollService {

	public enum IOService{CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
	private List<EmployeePayrollData> employeePayrollList;

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioservice) {
		if (ioservice.equals(IOService.DB_IO)){
			this.employeePayrollList= new EmployeePayrollDBService().readData();
		}
		return employeePayrollList;
	}

	public void updateEmployeeSalary(String name, double salary) {
		int result = new EmployeePayrollDBService().updateEmployeeData(name, salary);
		if (result == 0) return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null) employeePayrollData.salary = salary;

	}

	private EmployeePayrollData getEmployeePayrollData(String name) {
		return this.employeePayrollList.stream()
				.filter(employeePayrollData -> employeePayrollData.name.equals(name))
				.findFirst()
				.orElse(null);
	}

	public boolean checkEmployeeInSyncWithDB(String name) {
		List<EmployeePayrollData> employeePayrollDataList = new EmployeePayrollDBService().getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}

}