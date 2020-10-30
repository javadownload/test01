package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/hello", "/hello.do" })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet() {
    	System.out.println("HelloServlet() 생성");
    }

	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("HelloServlet() init() call");
	}

	public void destroy() {
		System.out.println("HelloServlet() destroy() call");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			     throws ServletException, IOException {
		
		System.out.println("HelloServlet() service() call");
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
       		
        PrintWriter out = response.getWriter();
        out.print("<h1> HelloServlet page 입니다. </h1>");
        
        String name = request.getParameter("name");
        out.print("<h3> name: "+ name+"</h3>");
        out.print("<h3> name: "+ name.charAt(0)+"OO</h3>");

        String addr = request.getParameter("addr");
        out.print("<h3> Address :"+ addr+"</h3>");
		
	
	}
	
//	@Override
//	public void doGet(HttpServletRequest request, HttpServletResponse response) 
//			    throws ServletException, IOException {
//		System.out.println("HelloServlet() doGet() call");
//		
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//       		
//        PrintWriter out = response.getWriter();
//        out.print("<h1> HelloServlet page 입니다. </h1>");
//        
//        String name = request.getParameter("name");
//        out.print("<h3> name: "+ name+"</h3>");
//        out.print("<h3> name: "+ name.charAt(0)+"OO</h3>");
//
//        String addr = request.getParameter("addr");
//        out.print("<h3> Address :"+ addr+"</h3>");
//        
//        
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		System.out.println("HelloServlet() doPost() call  ......");
//		doGet(request, response);
//	}

}





