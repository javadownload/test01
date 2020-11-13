package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO_Mariadb;
import dao.UserDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import vo.BookVO;
import vo.UserVO;

//@WebServlet("*.do")
public class DispatcherServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();

		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf('/'));
		System.out.println(action);

		if (action.equals("/login.do")) {
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
			return;
		}
		if (action.equals("/logout.do")) {
			if (session != null) {
				session.invalidate();
			}

			response.sendRedirect("./index.jsp");
			return;
		}
		if (action.equals("/bookList.do")) {
			
			if(session.getAttribute("login") == null) {
				//response.sendRedirect("login.jsp");
		        request.setAttribute("msg", "login이 필요합니다.");
	
				getServletContext().
				getRequestDispatcher("/login.jsp").
				forward(request, response);
				return;
			}
			
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			List<BookVO> list = service.bookList();

			
			request.setAttribute("bookList", list);
			String page = "/bookList.jsp";
			
			getServletContext().
			getRequestDispatcher(page).
			forward(request, response);
			
			return;
		}
		if (action.equals("/bookSearch.do")) {
			
				
			String condition = request.getParameter("condition");
			String keyword =	request.getParameter("keyword")	;
			
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			List<BookVO> list = service.searchBook(condition, keyword);

			request.setAttribute("bookList", list);
			String page = "/bookList.jsp";
			
			getServletContext().
			getRequestDispatcher(page).
			forward(request, response);
			return;
		}
		if (action.equals("/bookDelete.do")) {
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);
			
			String[] bookno = request.getParameterValues("bookno");
			
			for(String no :bookno) {
				service.bookDelete(Integer.parseInt(no));
			}
		
			response.sendRedirect("bookList.do");
			return;
		}
		if (action.equals("/addBook.do")) {
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);

			String title = request.getParameter("title");
			String publisher = request.getParameter("publisher");
			int price = Integer.parseInt(request.getParameter("price"));
			
			BookVO vo = new BookVO();
			vo.setPrice(price);
			vo.setTitle(title);
			vo.setPublisher(publisher);
			
			service.bookAdd(vo);
			
			response.sendRedirect("bookList.do");
			return;
		}
		if (action.equals("/viewBook.do")) {
			int no = Integer.parseInt(request.getParameter("bookno"));
			
			BookDAO_Mariadb dao = new BookDAO_Mariadb();
			BookService service = new BookServiceImpl(dao);

			BookVO vo = service.getBook(no);
			request.setAttribute("book", vo);
			
			getServletContext().
			getRequestDispatcher("/book_view.jsp").
			forward(request, response);
			return;
		}

	}

}
