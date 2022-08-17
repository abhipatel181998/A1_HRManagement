In code there are two package
1)ca.humber.model
	1)DAManager.java : 
	This java file contains function which helps  to  perform create,update,read and delete on employees and security table
		1)getEmployeeID	: 
		This function takes user and password and return employee_id if it found or return 0.
		Here We have used CallableStatement to call f_security function.
		2)addEmployee:
		This funciton take employee object and add it to database.We have used preparedStatement and also set add data using setInt  and setString.We have used thin driver to connect to the database.
		3)getAllEmployees:
		This function return list of employees in the form of arraylist.Here we have used statement to run sql.And added data to employees list using result set.
		4)getEmployeesByDepartmentID:
		This function takes departmentId and return arraylist of employees of that department id.Here we have used PreparedStatement with where clause.
		5)getEmployeeByID:
		This function takes employee_id and return that employee.In this we have used OracleCallableStatement to get data from procedure p_emp_info.And made employee object
		based on output data.
		6)updateEmployee:
		This function takes employees object and return 0 or 1 based on employee updated or not.If 1 it means employee updated.Here we have utilize UpdatableResultSet.
		First we have set all data which we want to update and called updateRow() to execute that.
		7)deleteEmployeeByID:
		This is takes employee id and delete it from employee table.We have used preparedStatement to append employee_id to sql query.
		8)batchUpdate
		This function takes array of sql query and done batch update according to query passed.First it loop through argument  array and add them to batch.
		And then executeBatch which will give use count which query is successfully run.And commit those changes.If any  error occured it will perform rollback.
	All above function handle SQLException and print SQLState,error message and error code.
	
	2)Employees.java
		This class is javaBeans class which has private data members and getters and setters methods using which we can store and aceess data that we got
		from the database.
		
	3)DBUtil.java
		This class has two methoods which are used to connect with the database. Both the methods read connection data from the database.properties file.
			1)getConnectionThinDriver():
				This method is used to connect with the database using thin driver and returns object of connection.
				
			2)getConnectionOciDriver()
				This method is used to connect with the database using OCI driver and returns object of connection.


2) ca.humber.test
	1) HRManagement.java
		This class provides interface to the user like login and after that they can perform differnet operations.
		For that we have used switch function so that user can select the operations they want to do.
		Using this interface class user can
		1.Get information of all the employees.
		2.Get information about employees working in the perticular department.
		3.Get information of perticular employee using it's id.
		4.Can add new employee.
		5.Can update information of existing employee.
		6.Can delete employee.
		7.Update login status of the exsting users of the application.
		
		This class basically calls all the methods from the DAManager.java class and provides users interface to interact with the database using a console application.
		

3)support
	1)SECURITY_TABLE.sql: 
	SQL script which contain create security query and insert some data to security table.
	2)SP_SECURITY.java: 
	SQL script which contain p_security package defination and body.Which includes funciton and procedure.
		
		
Test:
	Run HRManagement.java class
	
	Login using susan as username and susan123 as password.
	
		
