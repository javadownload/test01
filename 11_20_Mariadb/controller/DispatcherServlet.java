package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");


		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf('/'));

		System.out.println("요청 : "+action);
		HadlerMapping m = new HadlerMapping();
		
		Controller controller = m.getHandlerMapping(action);
		
		String view  = controller.handleRequest(request, response);
		
		
		getServletContext().
		getRequestDispatcher(view).
		forward(request, response);

	}
}
