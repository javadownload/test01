package ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class EmpManager {
	public static void main(String[] args) {
		List<Employee> sales = getEmployee("Sales Manager");
		List<Employee> stock = getEmployee("Stock Manager");
		
		System.out.println(sales);
		System.out.println();
		System.out.println(stock);
		
	}

	static List<Employee> getEmployee(String job){
		List<Employee> list = new ArrayList<Employee>();
		
		String sql = "select employee_id \"i d\", first_name name, salary 급여 " + 
				"from employees e, jobs j " + 
	"where e.job_id = j.job_id and upper(j.job_title) = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			//? 세팅 작업
			ps.setString(1, job.toUpperCase());
			
			rs = ps.executeQuery();  //select
			
			//결과값핸들링
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getString("i d"));
				emp.setName(rs.getString("name"));
				emp.setSal(rs.getInt("급여"));
			
				list.add(emp);
			}
		} catch (Exception e) {
          e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		
        return list;
	}
}

class Employee {
	String id;
	String name;
	int sal;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sal=" + sal + "]";
	}
	
	
}





