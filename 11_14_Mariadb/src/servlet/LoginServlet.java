
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO_Mariadb;
import service.UserService;
import service.UserServiceImpl;
import vo.UserVO;

@WebServlet({ "/login.do", "/logout.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("password");

		UserDAO_Mariadb dao = new UserDAO_Mariadb();
		UserService service = new UserServiceImpl(dao);

		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(pw);
		UserVO login = service.login(vo);

		if (login != null) {
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		} else {
			request.setAttribute("msg", "로그인 정보를 다시 입력하세요");
			// response.sendRedirect("./login.jsp");

			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}

		response.sendRedirect("./index.jsp");
	}

	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * request.setCharacterEncoding("utf-8");
	 * response.setContentType("text/html;charset=utf-8");
	 * 
	 * String id = request.getParameter("id"); String pw =
	 * request.getParameter("password");
	 * 
	 * PrintWriter out = response.getWriter();
	 * 
	 * if(id==null || pw==null || id.length()==0 || pw.length()==0) {
	 * request.setAttribute("msg", "로그인 정보를 다시 입력하세요");
	 * //response.sendRedirect("./login.jsp"); RequestDispatcher rd = null; rd =
	 * request.getRequestDispatcher("login.jsp"); rd.forward(request, response);
	 * return; } if(id.equals("java01") && pw.equals("1234")) { HttpSession session
	 * = request.getSession(); session.setAttribute("login", "java01");
	 * RequestDispatcher rd = null; rd = request.getRequestDispatcher("index.jsp");
	 * rd.forward(request, response);
	 * 
	 * }else { request.setAttribute("msg", "로그인 정보를 다시 입력하세요");
	 * //response.sendRedirect("./login.jsp");
	 * 
	 * RequestDispatcher rd = null; rd = request.getRequestDispatcher("login.jsp");
	 * rd.forward(request, response); }
	 * 
	 * }
	 */

}
