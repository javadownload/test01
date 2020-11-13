package test;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import service.BookService;
import service.BookServiceImpl;

public class Test03 {
	public static void main(String[] args) {
		
		BookDAO dao = new BookDAO_JDBC();
		BookService service = new BookServiceImpl(dao);
		
		service.bookList().forEach(i->{
			System.out.println(i);
		});
		
		System.out.println("-------title 검 색-----------------------------");

		service.searchBook("title", "자바").forEach(i->{
			System.out.println(i);
		});
		
		System.out.println("-------- 출판사 검색 ----------------------------");

		service.searchBook("publisher", "한").forEach(i->{
			System.out.println(i);
		});
		
		
	}
}
