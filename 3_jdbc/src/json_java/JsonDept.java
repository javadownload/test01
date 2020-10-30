
package json_java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.JDBCUtil;


public class JsonDept {

	public static void main(String[] args) throws InterruptedException {
		JsonDept w = new JsonDept();
		System.out.println(w.getDeptFromDB());
		System.out.println("-------------------------------");
		System.out.println(w.getDeptALLFromDB());
		
	}

	public String getDeptFromDB() {
		String select = "SELECT * FROM dept where deptno=10";
		//String select = "SELECT * FROM dept ";
		Map<String, String> dept = new HashMap<String, String>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				dept.put("deptno", rs.getString("deptno"));
				dept.put("dname", rs.getString("dname"));
				dept.put("loc", rs.getString("loc"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}

		return JSONObject.toJSONString(dept);
	}
	public String getDeptALLFromDB() {
		String select = "SELECT * FROM dept ";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		JSONArray deptAll = new JSONArray(); 
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("deptno", rs.getString("deptno"));
				map.put("dname", rs.getString("dname"));
				map.put("loc", rs.getString("loc"));
				JSONObject obj = new JSONObject(map);
				deptAll.add(map);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}
       
		return JSONArray.toJSONString(deptAll);
	}
}
