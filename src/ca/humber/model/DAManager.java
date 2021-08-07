package ca.humber.model;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

public class DAManager {

	public static int getEmployeeID(String user, String password) {

		Connection connection = null;

		CallableStatement statement = null;

		DBUtil dbUtil = new DBUtil();

		int employee_id = 0;
		try {

			connection = dbUtil.getConnectionThinDriver();
			
			//prepareCallable statement to call function
			
			statement = connection.prepareCall("{?= call P_SECURITY.F_SECURITY(?,?)}");

			//setting output type
			statement.registerOutParameter(1, Types.NUMERIC);

			//setting parameter for  passing it to prepareCall			
			statement.setString(2, user);
			statement.setString(3, password);

			statement.execute();

			employee_id = statement.getInt(1);
		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employee_id;
	}

	public static void addEmployee(Employees emp) {

		Connection con = null;

		PreparedStatement statement = null;

		DBUtil dbUtil = new DBUtil();

		try {

			con = dbUtil.getConnectionThinDriver();

			// Create prepared Statement
			
			String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";

			statement = con.prepareStatement(sql);

			// set value to prepareStatement
			statement.setInt(1, emp.getEmployee_id());
			statement.setString(2, emp.getFirst_name());
			statement.setString(3, emp.getLast_name());
			statement.setString(4, emp.getEmail());
			statement.setString(5, emp.getPhone_number());
			statement.setString(6, emp.getHire_date());
			statement.setString(7, emp.getJob_id());
			statement.setInt(8, emp.getSalary());
			statement.setInt(9, emp.getCommission_pct());
			statement.setInt(10, emp.getManager_id());
			statement.setInt(11, emp.getDepartment_id());

			int insertedRow = statement.executeUpdate();

			System.out.println(insertedRow + " row inserted!!");

		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {

			}
		}

	}

	public static ArrayList<Employees> getAllEmployees() {
		Connection con = null;

		Statement statement = null;

		ResultSet resultSet = null;

		DBUtil dbUtil = new DBUtil();

		ArrayList<Employees> employeesList = new ArrayList<>();
		try {

			con = dbUtil.getConnectionThinDriver();

			// Create Statement

			String sql = "select * from employees";

			statement = con.createStatement();

			resultSet = statement.executeQuery(sql);

			// loop through  resultSet and adding data to employee  list			
			while (resultSet.next()) {
				employeesList.add(new Employees(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11)));
			}

		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {

			}
		}
		return employeesList;
	}

	public static ArrayList<Employees> getEmployeesByDepartmentID(int depid) {
		Connection con = null;

		PreparedStatement statement = null;

		ResultSet resultSet = null;

		DBUtil dbUtil = new DBUtil();

		ArrayList<Employees> employeesList = new ArrayList<>();
		try {

			con = dbUtil.getConnectionThinDriver();

			// Create prepared Statement
			String sql = "select * from employees where DEPARTMENT_ID=?";

			statement = con.prepareStatement(sql);
			
			//setting data for prepare statement			
			statement.setInt(1, depid);

			resultSet = statement.executeQuery();

			// loop through  resultSet and adding data to the employee  list	
			while (resultSet.next()) {
				employeesList.add(new Employees(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11)));
			}

		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {

			}
		}
		return employeesList;
	}

	public static Employees getEmployeeByID(int empid) {
		Connection connection = null;

		OracleCallableStatement statement = null;

		DBUtil dbUtil = new DBUtil();

		ResultSet resultSet = null;

		Employees employee = null;

		try {

			connection = dbUtil.getConnectionThinDriver();
			
			// Oracle Callable statement 		
			statement = (OracleCallableStatement) connection.prepareCall("{call P_SECURITY.p_emp_info(?,?)}");

			//setting value to prepareCall			
			statement.setInt(1, empid);
			
			//setting output type			
			statement.registerOutParameter(2, OracleTypes.CURSOR);

			statement.executeQuery();

			resultSet = statement.getObject(2, ResultSet.class);

			while (resultSet.next()) {

				int commission_pct = 0;
				if (!(resultSet.getObject("commission_pct") == null)) {
					commission_pct = Integer.parseInt(resultSet.getObject("commission_pct").toString());
				}
				employee = new Employees(Integer.parseInt(resultSet.getObject("employee_id").toString()),
						resultSet.getObject("first_name").toString(), resultSet.getObject("last_name").toString(),
						resultSet.getObject("email").toString(), resultSet.getObject("phone_number").toString(),
						resultSet.getObject("hire_date").toString(), resultSet.getObject("job_id").toString(),
						Integer.parseInt(resultSet.getObject("salary").toString()), commission_pct,
						Integer.parseInt(resultSet.getObject("manager_id").toString()),
						Integer.parseInt(resultSet.getObject("department_id").toString()));
			}

		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			e.printStackTrace();
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return employee;
	}

	public static int updateEmployee(Employees emp) {

		Connection connection = null;

		Statement statement = null;

		ResultSet resultSet = null;

		DBUtil dbUtil = new DBUtil();

		int updatedRow = 0;
		try {

			connection = dbUtil.getConnectionThinDriver();

			//Sql query			
			String sql = "SELECT first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id FROM EMPLOYEES WHERE employee_id="
					+ emp.getEmployee_id();

			//create statement using result set			
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			resultSet = statement.executeQuery(sql);

			//setting result set to update data			
			while (resultSet.next()) {
				// Updates the data inside the result set.
				resultSet.updateString("first_name", emp.getFirst_name());
				resultSet.updateString("last_name", emp.getLast_name());
				resultSet.updateString("email", emp.getEmail());
				resultSet.updateString("phone_number", emp.getPhone_number());
				resultSet.updateString("hire_date", emp.getHire_date());
				resultSet.updateString("job_id", emp.getJob_id());
				resultSet.updateInt("salary", emp.getSalary());
				resultSet.updateInt("commission_pct", emp.getCommission_pct());
				resultSet.updateInt("manager_id", emp.getManager_id());
				resultSet.updateInt("department_id", emp.getDepartment_id());

				// Updates the table.
				resultSet.updateRow();
				updatedRow = 1;
			}

		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return updatedRow;
	}

	public static int deleteEmployeeByID(int empid) {

		Connection con = null;

		PreparedStatement statement = null;

		DBUtil dbUtil = new DBUtil();

		int deletedRow = 0;

		try {

			con = dbUtil.getConnectionThinDriver();

			// Create prepared Statement

			String sql = "delete from employees where employee_id=?";

			statement = con.prepareStatement(sql);

			// set value to prepareStatement
			statement.setInt(1, empid);

			deletedRow = statement.executeUpdate();

			System.out.println(deletedRow + " row deleted!!");

		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {

			}
		}
		return deletedRow;

	}

	public static boolean batchUpdate(String[] SQLs) throws SQLException {
		Connection con = null;

		Statement statement = null;

		DBUtil dbUtil = new DBUtil();

		try {

			con = dbUtil.getConnectionThinDriver();
			
			// turn off auto commit			
			con.setAutoCommit(false);

					
			statement = con.createStatement();
			
			//pushing sql query to batch
			for (int i = 0; i < SQLs.length; i++) {
				statement.addBatch(SQLs[i]);
			}
			
			//execute all query at once using execute Batch			
			int[] updateCounts = statement.executeBatch();

			//commit to save transaction  			
			con.commit();

		} catch (BatchUpdateException ex) {
			//undo  transaction  if error occur			
			con.rollback();
			return false;
		} catch (SQLException e) {
			System.err.println("Sql state: " + e.getSQLState());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {

			}
		}

		return true;

	}

}
