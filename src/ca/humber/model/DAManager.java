package ca.humber.model;

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
import oracle.jdbc.oracore.OracleType;

public class DAManager {

	public static int getEmployeeID(String user, String password) {

		Connection connection = null;

		CallableStatement statement = null;

		DBUtil dbUtil = new DBUtil();

		int employee_id = 0;
		try {

			connection = dbUtil.getConnectionThinDriver();
			statement = connection.prepareCall("{?= call P_SECURITY.F_SECURITY(?,?)}");

			statement.registerOutParameter(1, Types.NUMERIC);
			statement.setString(2, user);
			statement.setString(3, password);

			statement.execute();

			employee_id = statement.getInt(1);
		} catch (SQLException e) {
			System.err.println(e.getSQLState());
			System.err.println(e.getMessage());
			System.err.println(e.getErrorCode());
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

			System.out.println("Database is connected");

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
			System.err.println(e.getSQLState());
			System.err.println(e.getMessage());
			System.err.println(e.getErrorCode());
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

			System.out.println("Database is connected");

			// Create Statement

			String sql = "select * from employees";

			statement = con.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				employeesList.add(new Employees(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11)));
			}

		} catch (SQLException e) {
			System.err.println(e.getSQLState());
			System.err.println(e.getMessage());
			System.err.println(e.getErrorCode());
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

			System.out.println("Database is connected");

			// Create Statement

			String sql = "select * from employees where DEPARTMENT_ID=?";

			statement = con.prepareStatement(sql);

			statement.setInt(1, depid);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				employeesList.add(new Employees(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11)));
			}

		} catch (SQLException e) {
			System.err.println(e.getSQLState());
			System.err.println(e.getMessage());
			System.err.println(e.getErrorCode());
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

		try {

			connection = dbUtil.getConnectionThinDriver();
			statement =  (OracleCallableStatement) connection.prepareCall("{call P_SECURITY.p_emp_info(?,?)}");
			
			statement.setInt(1, empid);
			statement.registerOutParameter(2,OracleTypes.CURSOR);

			statement.executeQuery();
			
			resultSet = statement.getObject(2,ResultSet.class);
			
			while(resultSet.next()) {
				System.out.println(resultSet.getObject("job_id"));
			}

		} catch (SQLException e) {
			System.err.println(e.getSQLState());
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.err.println(e.getErrorCode());
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
		return null;
	}

}
