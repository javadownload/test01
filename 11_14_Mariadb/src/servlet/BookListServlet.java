package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/bookList.do")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		List<BookVO> list = service.bookList();

		
		request.setAttribute("bookList", list);
		String page = "/bookList.jsp";
		
		getServletContext().
		getRequestDispatcher(page).
		forward(request, response);
		
		
		
	}

}



