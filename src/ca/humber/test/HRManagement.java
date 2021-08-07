package ca.humber.test;

import java.util.Scanner;

import ca.humber.model.DAManager;
import ca.humber.model.Employees;

public class HRManagement {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		try {
//			System.out.println(DAManager.getEmployeeID("michael","michael123"));
//			Employees emp = new Employees(400,"Donald","OConnell","DCONNEL","650.507.9833","21-06-99","SH_CLERK",2600,0,124,50);
//			DAManager.addEmployee(emp);
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
					System.out.println("sucess!");
				} else {
					System.out.println("Hello " + employee.getFirst_name() + " " + employee.getLast_name() + ",");
					System.out.println(
							"You are not allowed to access this application, This application is only for HR!");
				}

			} else {
				System.out.println("Either your user name and password is wrong OR You do not have access to login.");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
