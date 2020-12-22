/**
 * 
 */
package com.example.jerseyrestapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohit Kapade
 *
 */
public class EmployeeRepository {
	
	//private List<Employee>  empList;

	private Connection conn;
	
	public EmployeeRepository() {
		
		String url = "jdbc:mysql://localhost:3306/restdb?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true";
		String username = "root";
		String password = "MySQL@12";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//empList = new ArrayList<Employee>();
//		
//		Employee e1 = new Employee();
//		e1.setId(10);
//		e1.setName("Rohit Kapade");
//		e1.setSalary(12000);
//		
//		Employee e2 = new Employee();
//		e2.setId(20);
//		e2.setName("Mahipal Singh");
//		e2.setSalary(10000);
//		
//		Employee e3 = new Employee();
//		e3.setId(30);
//		e3.setName("Manish Gupta");
//		e3.setSalary(11000);
//		
//		empList.add(e1);
//		empList.add(e2);
//		empList.add(e3);
	}

	public List<Employee> getEmpList() {
		
		List<Employee> empList = new ArrayList<>();
		String sql = "Select * from restdb.Employee";
		Statement st = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				
				empList.add(e);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		//this.empList = empList;
	}
	
	public Employee getEmployee(int id) {
//		Employee e1 = null;  // null pointer exception if employee with id not found.
//		
//		for(Employee e : empList) {
//			if(e.getId() == id)
//				return e;
//		}
//		
//		return e1;
		
		Employee e = null;  // null pointer exception if employee with id not found.
		String sql = "Select * from restdb.Employee where id=" + id;
		Statement st = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
			}
			
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		
		return e;
	}

	public void create(Employee e) {
		// TODO Auto-generated method stub
		//empList.add(new Employee(e.getId(), e.getName(), e.getSalary()));
		//System.out.println("empRepository create db object");
		//empList.add(e);
				
		if(null != getEmployee(e.getId())) {
			System.out.println("Employee with this ID found !! Hence can't create duplicate.");
			return; 
		}
		
		String sql = "insert into restdb.Employee values(?,?,?)";
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, e.getId());
			st.setString(2, e.getName());
			st.setDouble(3, e.getSalary());
			int rowCount = st.executeUpdate();
			if( 0 == rowCount) {
				System.out.println("****Error****** No record created !!");
			}
			
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		
	}

	public void update(Employee e) {
		// TODO Auto-generated method stub
		String sql = "update restdb.Employee set name=?, salary=? where id=?";
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, e.getName());
			st.setDouble(2, e.getSalary());
			st.setInt(3, e.getId());
			int rowCount = st.executeUpdate();
			if( 0 == rowCount) {
				System.out.println("****Error****** No record created !!");
			}
			
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
	}

	public void delete(Employee e) {
		// TODO Auto-generated method stub
		String sql = "delete from restdb.Employee where id=?";
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, e.getId());
			int rowCount = st.executeUpdate();
			if (0 == rowCount) {
				System.out.println("****Error****** No record created !!");
			}

		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}

	}
}
