package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO_Mariadb;
import service.UserService;
import service.UserServiceImpl;
import vo.UserVO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, 
			                    HttpServletResponse response) {
		
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String pw = request.getParameter("password");

		UserDAO_Mariadb dao = new UserDAO_Mariadb();
		UserService service = new UserServiceImpl(dao);

		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(pw);
		UserVO login = service.login(vo);

		if (login != null) {
			session.setAttribute("login", login);
			
			return "/index.jsp";

			
		} else {
			request.setAttribute("msg", "로그인 정보를 다시 입력하세요");
			// response.sendRedirect("./login.jsp");
			return "/login.jsp";
		}
	}
}
