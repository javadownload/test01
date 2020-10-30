package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form.do")
public class FormServlet extends HttpServlet {
       
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
       	
		PrintWriter out = response.getWriter();
		out.print("<h2>응답 내용 입니다....</h2>");
		
		
		String[] hobby =  request.getParameterValues("hobby");
		
		for(String data :hobby) {
			out.print("<h2>"+data+"</h2>");
		}
		
		
		
	}

}
