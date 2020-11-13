package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

@WebServlet({"/insertbook.do" })
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, 
			              HttpServletResponse response) 
			              throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		BookDAO dao = new BookDAO_JDBC();
		BookService service = new BookServiceImpl(dao);
		BookVO vo =  new BookVO();
		vo.setPublisher(request.getParameter("publisher"));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setTitle(request.getParameter("title"));
		try {
			service.bookAdd(vo);
			response.sendRedirect("bookList.do");
			//out.print("<h1>"+i+"개의 row 입력 완료");
		} catch (Exception e) {
			//out.print("<h2> 문제 발생 : "+e);
			request.setAttribute("exception", e);
			
			getServletContext().
			getRequestDispatcher("/error.jsp").
			forward(request, response);
		}
	}
}
