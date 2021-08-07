package ca.humber.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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

}
