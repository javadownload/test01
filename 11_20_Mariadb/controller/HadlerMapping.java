package controller;

import java.util.HashMap;
import java.util.Map;

public class HadlerMapping {
	public Map<String,Controller> map = new HashMap<String, Controller>();
	
	public HadlerMapping() {
		map.put("/bookList.do",new ListController());
		map.put("/logout.do",new LogoutController());
		map.put("/login.do",new LoginController());
	}
	
	public Controller getHandlerMapping(String path) {
		return map.get(path);
	}

}
