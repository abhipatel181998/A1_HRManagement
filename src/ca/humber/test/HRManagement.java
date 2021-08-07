package ca.humber.test;

import java.util.ArrayList;
import java.util.Scanner;

import ca.humber.model.DAManager;
import ca.humber.model.Employees;

public class HRManagement {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		try {
//			System.out.println(DAManager.getEmployeeID("michael","michael123"));
//			Employees emp = new Employees(198,"Donald","OConnell","DCONNEL","650.507.9833","21-06-99","SH_CLERK",2600,0,124,50);
//			System.out.println(DAManager.updateEmployee(emp));
//			
//			List<Employees> emp = DAManager.getEmployeesByDepartmentID(70);
//			
//			for(Employees e:emp) {
//				System.out.println(e.getEmployee_id());
//			}

//			DAManager.getEmployeeByID(210);

			System.out.print("Enter your username: ");
			String user = in.next();

			System.out.print("Enter password: ");
			String password = in.next();

			int employee_id = DAManager.getEmployeeID(user, password);

			if (employee_id != 0) {

				Employees employee = DAManager.getEmployeeByID(employee_id);
				if (employee.getJob_id().equals("HR_REP")) {

					System.out.println("\nWelecome, " + employee.getFirst_name() + " " + employee.getLast_name() + ",");

					while (true) {
						System.out.println("\nOption available.");
						System.out.println("----------------------");

						System.out.println("\n1. View all employees.");
						System.out.println("2. View employees by departments.");
						System.out.println("3. View employee information.");
						System.out.println("4. Add new employee");
						System.out.println("5. Update employee information.");
						System.out.println("6. Delete employee.");
						System.out.println("7. Insert new user for this application.");
						System.out.println("0. Exit from the application.\n");

						System.out.print("Enter the option: ");
						int choise = in.nextInt();

						switch (choise) {

						case 0:
							System.out.println("\nGood bye.");
							return;

						case 1:
							ArrayList<Employees> employees = DAManager.getAllEmployees();

							System.out.println("Employee Id \t EmployeeName \t Job Id \t Salary");
							System.out.println("------------------------------------------------------------------------");
							for (Employees emp : employees) {
								System.out.println(emp.getEmployee_id() + "\t" + emp.getFirst_name() + " "
										+ emp.getLast_name() + "\t" + emp.getJob_id() + "\t" + emp.getSalary());
								System.out.println("------------------------------------------------------------------------");
							}

							break;

							
							
						case 2:
							System.out.print("Enter department id: ");
							int department_id = in.nextInt();
							
							ArrayList<Employees> employeesByDep = DAManager.getEmployeesByDepartmentID(department_id);

							System.out.println("Employee Id \t EmployeeName \t Job Id \t Salary");
							System.out.println("------------------------------------------------------------------------");
							for (Employees emp : employeesByDep) {
								System.out.println(emp.getEmployee_id() + "\t" + emp.getFirst_name() + " "
										+ emp.getLast_name() + "\t" + emp.getJob_id() + "\t" + emp.getSalary());
								System.out.println("------------------------------------------------------------------------");
							}
							
							break;
							
							
							
						case 3:
							System.out.print("Enter employee id: ");
							int employeeId = in.nextInt();
							
							Employees emp = DAManager.getEmployeeByID(employeeId);

							System.out.println("Employee Id \t EmployeeName \t Job Id \t Salary");
							System.out.println("------------------------------------------------------------------------");
								System.out.println(emp.getEmployee_id() + "\t" + emp.getFirst_name() + " "
										+ emp.getLast_name() + "\t" + emp.getJob_id() + "\t" + emp.getSalary());
								System.out.println("------------------------------------------------------------------------");
							
							break;

						default:
							System.out.println("Wrong choise entered.");
							break;

						}
					}

				} else {
					System.out.println("Hello " + employee.getFirst_name() + " " + employee.getLast_name() + ",");
					System.out.println(
							"You are not allowed to access this application, This application is only for HR!");
				}

			} else {
				System.out.println("Either your user name and password is wrong OR You do not have access to login.");
			}

//			String[] sqlbatch = { "insert into security values(200,'abhi','abhi123','A')",
//					"insert into security values(199,'harshil','harshil123','A')" };
//
//			
//			System.out.println(DAManager.batchUpdate(sqlbatch));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
