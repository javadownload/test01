package test;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

public class Test02 {
	public static void main(String[] args) throws Exception {
		
		BookDAO dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		
		BookVO vo = new BookVO();
		vo.setTitle("");
		vo.setPrice(900);
		vo.setPublisher("");
		
		service.bookAdd(vo);
		
		service.bookList().forEach(i->{
			System.out.println(i);
		});

	}
}
