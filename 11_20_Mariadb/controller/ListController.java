package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

public class ListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, 
			                    HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null) {
			//response.sendRedirect("login.jsp");
	        request.setAttribute("msg", "login이 필요합니다.");
			return "/login.jsp";
		}
		
		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		List<BookVO> list = service.bookList();

		
		request.setAttribute("bookList", list);
	    
		return "/bookList.jsp";
	}
}
