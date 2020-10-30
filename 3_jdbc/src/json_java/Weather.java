
package json_java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import util.JDBCUtil;


public class Weather {
	static Map<String, String> map = new HashMap<String, String>();
	private static final long SECOND = 1000;
	private static final long MINUTE = 60 * SECOND;
	private static final long HOUR = 60 * MINUTE;

	public static void main(String[] args) throws InterruptedException {
		Weather w = new Weather();
		String lat = "37.50065903853966";
		String lon = "127.03946862393614";
		while(true)	{
			System.out.println(w.getWeatherFromAPI(lat, lon));
			System.out.println("inserted row: "+ insertWeather(map));
			System.out.println(w.getWeatherFromDB());
			Thread.sleep(HOUR);
		}
	}

	private void receiveDust() {
		BufferedReader br = null;
		Calendar cal = Calendar.getInstance();
		try {
			String result = "";
			String line;
			JSONParser jsonParser = new JSONParser();
			String serviceKey = "lWyXzvx6gba4CMkvCzEHrp%2FktvU3U4lreQYOIx9MO1yYn%2BMicRUNTEwYWOYxOfFYgxGEEHzw4YHuVW2mC80GRA%3D%3D";
			String dustAddress = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=강남구&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey="
					+ serviceKey + "&ver=1.3&_returnType=json";
			URL urlDust = new URL(dustAddress);
			HttpURLConnection urlDustconnection = (HttpURLConnection) urlDust.openConnection();
			urlDustconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlDustconnection.getInputStream(), "UTF-8"));
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}

			JSONArray dustArray = (JSONArray) ((JSONObject) jsonParser.parse(result)).get("list");
			for (Object obj : dustArray) {

				JSONObject dustObject = (JSONObject) jsonParser.parse(obj.toString());
				if ((dustObject.get("dataTime").toString().substring(11, 13)) != null) {
					if (cal.get(Calendar.HOUR_OF_DAY) == Integer
							.parseInt(dustObject.get("dataTime").toString().substring(11, 13))) {
						map.put("pm10Value", dustObject.get("pm10Value").toString());
						map.put("pm10Grade", dustObject.get("pm10Grade").toString());
						map.put("pm25Value", dustObject.get("pm25Value").toString());
						map.put("pm25Grade", dustObject.get("pm25Grade").toString());
						map.put("dataTime", dustObject.get("dataTime").toString());
						map.put("location", "강남구");
					} else {
						if (14 == Integer.parseInt(dustObject.get("dataTime").toString().substring(11, 13))) {
							map.put("pm10Value", dustObject.get("pm10Value").toString());
							map.put("pm10Grade", dustObject.get("pm10Grade").toString());
							map.put("pm25Value", dustObject.get("pm25Value").toString());
							map.put("pm25Grade", dustObject.get("pm25Grade").toString());
							map.put("dataTime", dustObject.get("dataTime").toString());
							map.put("location", "강남구");
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void receiveWeather(String lat, String lon) {
		BufferedReader br = null;
		try {
			String line = null;
			String result = "";
			JSONParser jsonParser = new JSONParser();
			String appID = "d1e7b1d06e72c560ef8f60972d8e3413";
			String tempAddress = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
					+ "&lang=kr&appid=" + appID;
			URL urlDust = new URL(tempAddress);
			HttpURLConnection urlDustconnection = (HttpURLConnection) urlDust.openConnection();
			urlDustconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlDustconnection.getInputStream(), "UTF-8"));
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}

			String weatherID = ((JSONObject) (((JSONArray) ((JSONObject) jsonParser.parse(result)).get("weather"))
					.get(0))).get("id").toString();
			map.put("weatherID", weatherID);
			String weather = ((JSONObject) (((JSONArray) ((JSONObject) jsonParser.parse(result)).get("weather"))
					.get(0))).get("description").toString();
			map.put("weather", weather);
			JSONObject object = (JSONObject) ((JSONObject) jsonParser.parse(result)).get("main");
			String tempNow = String.format("%3.1f", ((double) object.get("temp") - 273));
			String tempMin = String.format("%3.1f", ((double) object.get("temp_min") - 273));
			String humidity = object.get("humidity").toString();
			String tempMax = String.format("%3.1f", ((double) object.get("temp_max") - 273));
			map.put("tempNow", tempNow);
			map.put("tempMin", tempMin);
			map.put("humidity", humidity);
			map.put("tempMax", tempMax);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getWeatherFromAPI(String lat, String lon) {
		receiveWeather(lat, lon);
		receiveDust();
		return JSONObject.toJSONString(map);
	}

	static int insertWeather(Map<String, String> map) {
		String select = "SELECT * FROM (SELECT dataTime FROM weather ORDER BY datatime desc) t where rownum = 1";
		String insert = "INSERT INTO weather(dataTime, tempMax,tempMin, weather, humidity, tempNow, pm10Value, pm10Grade, pm25Value, pm25Grade, location, weatherID)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		try {
			con = JDBCUtil.getConnection();

			st = con.prepareStatement(select);
			ps = con.prepareStatement(insert);
			rs = st.executeQuery(select);
			rs.next();
			if (!(rs.getString("dataTime").equals(map.get("dataTime")))) {
				ps.setString(1, map.get("dataTime"));
				ps.setString(2, map.get("tempMax"));
				ps.setString(3, map.get("tempMin"));
				ps.setString(4, map.get("weather"));
				ps.setString(5, map.get("humidity"));
				ps.setString(6, map.get("tempNow"));
				ps.setString(7, map.get("pm10Value"));
				ps.setString(8, map.get("pm10Grade"));
				ps.setString(9, map.get("pm25Value"));
				ps.setString(10, map.get("pm25Grade"));
				ps.setString(11, map.get("location"));
				ps.setString(12, map.get("weatherID"));
			} else {
				return 0;
			}

			row = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return row;
	}

	public String getWeatherFromDB() {
		String select = "SELECT * FROM (SELECT * FROM weather ORDER BY datatime desc) t where rownum = 1";
		Map<String, String> temp = new HashMap<String, String>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				temp.put("dataTime", rs.getString("dataTime"));
				temp.put("tempMax", rs.getString("tempMax"));
				temp.put("tempMin", rs.getString("tempMin"));
				temp.put("weather", rs.getString("weather"));
				temp.put("humidity", rs.getString("humidity"));
				temp.put("tempNow", rs.getString("tempNow"));
				temp.put("pm10Value", rs.getString("pm10Value"));
				temp.put("pm10Grade", rs.getString("pm10Grade"));
				temp.put("pm25Value", rs.getString("pm25Value"));
				temp.put("pm25Grade", rs.getString("pm25Grade"));
				temp.put("location", rs.getString("location"));
				temp.put("weatherID", rs.getString("weatherID"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(con, st, rs);
		}

		return JSONObject.toJSONString(temp);
	}
}
