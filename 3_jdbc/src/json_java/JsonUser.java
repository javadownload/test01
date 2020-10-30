
package json_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.JDBCUtil;


public class JsonUser {

	public static void main(String[] args) throws InterruptedException {
		JsonUser w = new JsonUser();
		System.out.println(w.login("java01","1234"));
		System.out.println("-------------------------------");
		System.out.println(w.idCheck("java01"));

		System.out.println("-------------------------------");
		System.out.println(w.getUserALLFromDB());
		System.out.println("-------------------------------");
		
	}
	public String idCheck(String id) {
		String select = "SELECT * FROM users where user_id = ?";
		Map<String, String> user = new HashMap<String, String>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			st = con.prepareStatement(select);
			st.setString(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				user.put("user_id", rs.getString("user_id"));
				user.put("name", rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}

		return JSONObject.toJSONString(user);
	}
	public String login(String id,String pw) {
		String select = "SELECT * FROM users where user_id = ? and pw=?";
		Map<String, String> user = new HashMap<String, String>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			st = con.prepareStatement(select);
			st.setString(1, id);
			st.setString(2, pw);
			rs = st.executeQuery();
			while (rs.next()) {
				user.put("user_id", rs.getString("user_id"));
				user.put("name", rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}

		return JSONObject.toJSONString(user);
	}
	public String getUserALLFromDB() {
		String select = "SELECT * FROM users ";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		JSONArray userAll = new JSONArray(); 
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				Map<String, String> user = new HashMap<String, String>();
				user.put("user_id", rs.getString("user_id"));
				user.put("name", rs.getString("name"));
				JSONObject obj = new JSONObject(user);
				userAll.add(user);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}
       
		return JSONArray.toJSONString(userAll);
	}
}
