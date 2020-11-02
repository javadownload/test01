package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/login.do","/logout.do"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			                                      throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		response.sendRedirect("./index.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			                                      throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8"); 
		
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        
        System.out.println("/login.do ");
        System.out.println(id+"/"+pw);
        
        if(id==null||pw==null||id.length()==0||pw.length()==0) {
        	//response.sendRedirect("./login.jsp");
        	request.setAttribute("msg", "pw 정보를  입력하세요");
        	request.setAttribute("id", id);
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	return;
        }
        if(id.equals("java01") && pw.equals("1234")) {
        	HttpSession session = request.getSession();
        	session.setAttribute("login", id+"/홍자바");
        	
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        	
        }else {
        	request.setAttribute("msg", "로그인 실패 ");
        	request.setAttribute("id", id);
        	
        	request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}





