package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

@WebServlet({ "/bookList.do" })
public class ListBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, 
			               HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
      
		
//		HttpSession session = request.getSession();
//        String login = (String) session.getAttribute("login");
//        System.out.println("login : "+login);
//		if(login == null) {
//			//response.sendRedirect("login.jsp");
//	         request.setAttribute("msg", "login이 필요합니다.");
//
//			getServletContext().
//			getRequestDispatcher("/login.jsp").
//			forward(request, response);
//			return;
//		}
        
		BookDAO dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		List<BookVO> list = service.bookList();
		
		list.forEach(i -> System.out.println(i));
		
		//PrintWriter out = response.getWriter();
		
		//list.forEach(i -> out.print("<h3>"+i+"</h3>"));

		
		request.setAttribute("bookList", list);
		
		getServletContext().
		getRequestDispatcher("/bookList3.jsp").
		forward(request, response);
		
	}

}





