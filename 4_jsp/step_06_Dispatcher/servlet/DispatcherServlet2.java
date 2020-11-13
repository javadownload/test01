package servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import dao.BookDAO_Oracle;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

//@WebServlet("*.do")
//@MultipartConfig(maxFileSize = 1024*1024*5)
public class DispatcherServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			                                      throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf('/'));
		System.out.println(action);
        if(action.equals("/login.do")) {
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
        	
        	return;
        }
        if(action.equals("/bookList.do")) {
        	 HttpSession session = request.getSession();
             String login = (String) session.getAttribute("login");
             if(login ==  null) {
             	request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
             	request.getRequestDispatcher("login.jsp").forward(request, response);
             	return;
             }
             
     		
     		BookDAO dao = new BookDAO_JDBC();
     		BookService service = new BookServiceImpl(dao);
     		List<BookVO> list = service.bookList();
     		
     		request.setAttribute("bookList", list);
     		String view ="/bookList.jsp";
     		
     		getServletContext().getRequestDispatcher(view).forward(request, response);
     		
        	
        	return;
        }
        if(action.equals("/deleteBook.do")) {
        	String[] bookid = request.getParameterValues("bookid");
    		String img = request.getParameter("img");
    		String fname=img.substring(img.lastIndexOf('/')+1);
    		String path = request.getRealPath("/upload/");
    		
    		File f = new File(path+fname);
    		if(f.exists()) {
    			f.delete();
    		}
            
    		BookDAO dao = new BookDAO_JDBC();
    		BookService service = new BookServiceImpl(dao);
    		try {
    			for(String data :bookid) {
    				
    			   service.deleteBook(Integer.parseInt(data));
    			}
    			response.sendRedirect("bookList.do");
    		} catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
        	
        	return;
        }
        if(action.equals("/logout.do")) {
        	HttpSession session = request.getSession();
    		if(session != null) {
    			session.invalidate();
    		}
    		response.sendRedirect("./index.jsp");
    		return;
        }
        
       
        if(action.equals("/insertBook.do")) {
        	BookDAO dao = new BookDAO_JDBC();
    		BookService service = new BookServiceImpl(dao);
    	
    		BookVO vo = new BookVO();
    		vo.setPrice(Integer.parseInt(request.getParameter("price")));
    		vo.setTitle(request.getParameter("title"));
            vo.setPublisher(request.getParameter("publisher"));
    		
            String path = request.getRealPath("/upload/");
            
            Collection<Part> parts = request.getParts();
            for(Part p :parts) {
            	if(p.getContentType() != null) {
            		String fileName = p.getSubmittedFileName();
            		if(fileName != null && fileName.length() != 0) {
            			p.write(path+fileName);
            			//vo.setImg("./upload/"+fileName);
            			System.out.println(vo);
            		}
            	}
            }
            
            try {
               service.bookAdd(vo);
               response.sendRedirect("bookList.do");
            }catch (Exception e) {
            	request.setAttribute("exception", new Exception("등록 데이터  확인후 다시 등록하세요"));
            	getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            }
        	return;
        }
        
        if(action.equals("/search.do")) {
       	    HttpSession session = request.getSession();
            String login = (String) session.getAttribute("login");
            if(login ==  null) {
            	request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
            	request.getRequestDispatcher("login.jsp").forward(request, response);
            	return;
            }
            
    		String condition = request.getParameter("condition");
    		String keyword = request.getParameter("keyword");
    		
    		BookDAO dao = new BookDAO_JDBC();
    		BookService service = new BookServiceImpl(dao);
    		List<BookVO> list = service.searchBook(condition, keyword);
    		
    		request.setAttribute("bookList", list);
    		String view ="/bookList.jsp";
    		
    		getServletContext().getRequestDispatcher(view).forward(request, response);
    		
       	
       	return;
       }
        
        
	}
}





