package ca.humber.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import ca.humber.model.DAManager;
import ca.humber.model.Employees;

public class HRManagement {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in); // Creating object of Scanner class to take input from user.
		try {

			// Asking user's user name and password to connect to the application.
			System.out.print("Enter your username: ");
			String user = in.next();

			System.out.print("Enter password: ");
			String password = in.next();

			int employee_id = DAManager.getEmployeeID(user, password); // if get 0 then user is not authorized to login.

			// If user is authorized to login.
			if (employee_id != 0) {

				// Getting all the information of the employee who is logged in.
				Employees employee = DAManager.getEmployeeByID(employee_id);

				// If user is HR then allow to access application.
				if (employee.getJob_id().equals("HR_REP")) {

					// Welcome message for user.
					System.out.println("\nWelecome, " + employee.getFirst_name() + " " + employee.getLast_name() + ",");

					while (true) {
						// Operations that user can perform.
						System.out.println("\nOption available.");
						System.out.println("----------------------");

						System.out.println("\n1. View all employees.");
						System.out.println("2. View employees by departments.");
						System.out.println("3. View employee information.");
						System.out.println("4. Add new employee");
						System.out.println("5. Update employee information.");
						System.out.println("6. Delete employee.");
						System.out.println("7. Update login staus of the employees.");
						System.out.println("0. Exit from the application.\n");

						// Asking user to enter the choice from given option.
						System.out.print("Enter the option: ");
						int choise = in.nextInt();

						switch (choise) {

						// Exit from the application.
						case 0:
							System.out.println("\nGood bye.");
							return;

						// Showing all the employees information.
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

						// Showing all the information for employees working in the particular
						// department.
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

						// Shoing information for particular employee.
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

						// Adding new employee record
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

							System.out.print("\nEnter Hire Date (yy-mm-dd): ");
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

						// Updating information of existing employee.
						case 5:
							System.out.print("\nEneter employee id for which you want to update details: ");
							int emp_id_u = in.nextInt();

							Employees employee_u = DAManager.getEmployeeByID(emp_id_u);

							if (employee_u != null) {
								System.out.println("\nNote: Enter -1 for values you don't want to update.");

								System.out.print("\nEneter updated First Name: ");
								String f_name_u = in.next();
								if (!(f_name_u.equals("-1")))
									employee_u.setFirst_name(f_name_u);

								System.out.print("\nEneter upgdated Last Name: ");
								String l_name_u = in.next();
								if (!(l_name_u.equals("-1")))
									employee_u.setLast_name(l_name_u);

								System.out.print("\nEneter updated Email: ");
								String email_u = in.next();
								if (!(email_u.equals("-1")))
									employee_u.setEmail(email_u);

								System.out.print("\nEneter updated Phone Number: ");
								String phone_u = in.next();
								if (!(phone_u.equals("-1")))
									employee_u.setPhone_number(phone_u);

								System.out.print("\nEneter updated Hire Date(yy-mm-dd): ");
								String hireDate_u = in.next();
								if (!(hireDate_u.equals("-1")))
									employee_u.setHire_date(hireDate_u);
								else
									System.out.println(employee_u.getHire_date());
								

								System.out.print("\nEneter updated Job Id: ");
								String job_id_u = in.next();
								if (!(job_id_u.equals("-1")))
									employee_u.setJob_id(job_id_u);

								System.out.print("\nEneter updated Salary: ");
								double salary_u = in.nextDouble();
								if (!(salary_u == -1))
									employee_u.setSalary(salary_u);

								System.out.print("\nEnter updated Commission Pct: ");
								double comm_pct_u = in.nextDouble();
								if (!(comm_pct_u == -1))
									employee_u.setCommission_pct(comm_pct_u);
								else if (comm_pct_u == -1)
									employee_u.setCommission_pct(0);

								System.out.print("\nEnter updated Manager Id: ");
								int mng_id_u = in.nextInt();
								if (!(mng_id_u == -1))
									employee_u.setManager_id(mng_id_u);

								System.out.print("\nEnter updated Department Id: ");
								int dep_id_u = in.nextInt();
								if (!(dep_id_u == -1))
									employee_u.setDepartment_id(dep_id_u);

								int rowsUpdated = DAManager.updateEmployee(employee_u);

								System.out.println(rowsUpdated + "Employees updated.");
							}
							// When no record is found for the entered employee id.
							else {
								System.out.println("\nNo record found for the given employee id.");
							}

							break;

						// Delete the employee record.
						case 6:
							System.out.print("\nEnter employee id: ");
							int emp_id_d = in.nextInt();

							int rowsDeleted = DAManager.deleteEmployeeByID(emp_id_d);
							System.out.println("\n" + rowsDeleted + " employee deleted.");

							break;

						// Change status of the users in batch who can login in in the application.
						case 7:
							char status = 0;

							System.out.println("Note: Enter 1 for active and 2 for inactive.");
							System.out.println("Choose updated status: ");
							int choise_s = in.nextInt();

							switch (choise_s) {
							case 1:
								status = 'A';
								break;
							case 2:
								status = 'I';
								break;
							default:
								System.out.println("\nWrong input.");
								break;
							}

							if (choise_s == 1 || choise_s == 2) {
								String[] sqls = new String[100];

								int emp_id_s = 0;
								int index = 0;
								System.out.println("\nNote: Enter -1 to stop.");
								while (emp_id_s != -1) {
									System.out.println("\nEnter employee id to update status: ");
									emp_id_s = in.nextInt();

									if (emp_id_s != -1) {
										sqls[index] = "UPDATE security SET sec_status = '" + status
												+ "' WHERE employee_id=" + emp_id_s;

										index++;
									}
								}

								DAManager.batchUpdate(sqls);
							}

							break;

						default:
							System.out.println("\nWrong choise entered.");
							break;

						}
					}

				}
				// If user is not HR.
				else {
					System.out.println("Hello " + employee.getFirst_name() + " " + employee.getLast_name() + ",");
					System.out.println(
							"You are not allowed to access this application, This application is only for HR!");
				}

			}
			// When user's status is inactive or the user name and password is not correct.
			else {
				System.out.println("Either your user name and password is wrong OR You do not have access to login.");
			}

		}
		// Handling exceptions.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
