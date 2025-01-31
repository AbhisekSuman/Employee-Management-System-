package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class EmployeeApp {
	static String path = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/kodnest";
	static String user = "root";
	static String pw = "2003";
	
	 Connection connection = null;
	 PreparedStatement preparedStatement = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 
	 
	 {
		try {
			Class.forName(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ResultSet getEmployees() {
		
		String sql = "SELECT * FROM EMPLOYEE;";
		try {
			connection = DriverManager.getConnection(url, user, pw);
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			
			
			
			return resultset;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return resultset;
	}
	
	public ResultSet getEmployee(int id) {
		String sql = "SELECT * FROM EMPLOYEE WHERE ID=?;";
		try {
			connection = DriverManager.getConnection(url, user, pw);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultset = preparedStatement.executeQuery();
			
			
			
			return resultset;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return resultset;
	}
	
	public void addEmployee(String name, String dept, int salary) {
		String sql = "INSERT INTO EMPLOYEE(name,department,salary) VALUES(?,?,?);";
		try {
			connection = DriverManager.getConnection(url, user, pw);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, dept);
			preparedStatement.setInt(3, salary);
			int res = preparedStatement.executeUpdate();
			
			System.out.println(res + " row(s) affected");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
//				if(connection != null)
					connection.close();
				statement.close();
				resultset.close();
			} catch (Exception e2) {		
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
	}
	
	public void removeEmployee(int id) {
		String sql = "DELETE FROM EMPLOYEE WHERE ID=?;";
		try {
			connection = DriverManager.getConnection(url, user, pw);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int res = preparedStatement.executeUpdate();
			
			System.out.println(res + " row(s) affected");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null)
					connection.close();
				statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}	
		}
	}
	
	public void updateEmployee(int id, String name, String dept, int salary) {
		String sql = "UPDATE EMPLOYEE SET NAME=?, DEPARTMENT=?, SALARY=? WHERE ID=?;";
		try {
			connection = DriverManager.getConnection(url, user, pw);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, dept);
			preparedStatement.setInt(3, salary);
			preparedStatement.setInt(4, id);
			int res = preparedStatement.executeUpdate();
			
			System.out.println(res + " row(s) affected");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			try {
//				if(connection != null)
//					connection.close();
//				statement.close();
//				resultset.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}	
			
		}
	}
}
