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

@WebServlet({ "/viewBook.do" })
public class ViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, 
			               HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
      
		int no = Integer.parseInt(request.getParameter("bookno"));

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

		BookVO vo = service.getBook(no);
		request.setAttribute("book", vo);
		
		getServletContext().
		getRequestDispatcher("/book_view.jsp").
		forward(request, response);
		
	}

}





