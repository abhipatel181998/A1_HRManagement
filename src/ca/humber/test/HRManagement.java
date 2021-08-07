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

							System.out.println("\nEmployee Id \t EmployeeName \t Job Id \t Salary");
							System.out.println(
									"------------------------------------------------------------------------");
							for (Employees emp : employees) {
								System.out.println(emp.getEmployee_id() + "\t" + emp.getFirst_name() + " "
										+ emp.getLast_name() + "\t" + emp.getJob_id() + "\t" + emp.getSalary());
								System.out.println(
										"------------------------------------------------------------------------");
							}

							break;

						case 2:
							System.out.print("\nEnter department id: ");
							int department_id = in.nextInt();

							ArrayList<Employees> employeesByDep = DAManager.getEmployeesByDepartmentID(department_id);

							System.out.println("\nEmployee Id \t EmployeeName \t Job Id \t Salary");
							System.out.println(
									"------------------------------------------------------------------------");
							for (Employees emp : employeesByDep) {
								System.out.println(emp.getEmployee_id() + "\t" + emp.getFirst_name() + " "
										+ emp.getLast_name() + "\t" + emp.getJob_id() + "\t" + emp.getSalary());
								System.out.println(
										"------------------------------------------------------------------------");
							}

							break;

						case 3:
							System.out.print("\nEnter employee id: ");
							int employeeId = in.nextInt();

							Employees emp = DAManager.getEmployeeByID(employeeId);

							System.out.println("\nEmployee Id \t EmployeeName \t Job Id \t Salary");
							System.out.println(
									"------------------------------------------------------------------------");
							if (emp != null)
								System.out.println(emp.getEmployee_id() + "\t" + emp.getFirst_name() + " "
										+ emp.getLast_name() + "\t" + emp.getJob_id() + "\t" + emp.getSalary());

							break;

						case 4:

							System.out.print("\nEnter employee id: ");
							int emp_id_i = in.nextInt();
							while (emp_id_i == 0) {
								System.out.println("Please Enter employee id: ");
								emp_id_i = in.nextInt();
							}

							System.out.print("\nEnter First Name: ");
							String f_name_i = in.next();

							System.out.print("\nEnter Last Name: ");
							String l_name_i = in.next();

							System.out.print("\nEnter Email: ");
							String email_i = in.next();

							System.out.print("\nEnter Phone Number: ");
							String phone_i = in.next();

							System.out.print("\nEnter Hire Date: ");
							String hireDate_i = in.next();

							System.out.print("\nEnter Job Id: ");
							String job_id_i = in.next();

							System.out.print("\nEnter Salary: ");
							int salary_i = in.nextInt();

							System.out.print("\nEneter Commission Pct: ");
							int comm_i = in.nextInt();

							System.out.print("\nEneter Manager Id: ");
							int mng_id_i = in.nextInt();

							System.out.print("\nEnetr Department Id: ");
							int dep_id_i = in.nextInt();

							Employees employee_i = new Employees(emp_id_i, f_name_i, l_name_i, email_i, phone_i,
									hireDate_i, job_id_i, salary_i, comm_i, mng_id_i, dep_id_i);

							DAManager.addEmployee(employee_i);

							break;

						case 5:

							break;

						case 6:
							System.out.print("\nEnter employee id: ");
							int emp_id_d = in.nextInt();

							int rowsDeleted = DAManager.deleteEmployeeByID(emp_id_d);
							System.out.println("\n" + rowsDeleted + " employee deleted.");
							
							break;
							
							
							
						case 7:
							

						default:
							System.out.println("\nWrong choise entered.");
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
