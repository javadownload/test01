package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

@WebServlet("/bookSearch.do")
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

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
		
	}

}
