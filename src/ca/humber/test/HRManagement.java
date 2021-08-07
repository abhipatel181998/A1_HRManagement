package ca.humber.test;



import java.util.List;

import ca.humber.model.DAManager;
import ca.humber.model.DBUtil;
import ca.humber.model.Employees;

public class HRManagement {

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
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
			
			DAManager.getEmployeeByID(210);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
