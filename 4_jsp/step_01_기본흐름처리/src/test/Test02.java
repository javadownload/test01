package test;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

public class Test02 {
	public static void main(String[] args) {
		
		BookDAO dao = new BookDAO_JDBC();
		BookService service = new BookServiceImpl(dao);
		
		BookVO vo = new BookVO();
		vo.setTitle("연습중1");
		vo.setPrice(900);
		vo.setPublisher("명지대");
		
		service.bookAdd(vo);
		
		service.bookList().forEach(i->{
			System.out.println(i);
		});

	}
}
