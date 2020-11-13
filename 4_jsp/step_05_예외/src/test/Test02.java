package test;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import service.BookService;
import service.BookServiceImpl;

public class Test02 {
	public static void main(String[] args) {
		
		BookDAO dao = new BookDAO_JDBC();
		BookService service = new BookServiceImpl(dao);
		
		service.bookList().forEach(i->{
			System.out.println(i);
		});

	}
}
