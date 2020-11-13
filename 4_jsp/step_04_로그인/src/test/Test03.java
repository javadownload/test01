package test;

import java.util.List;

import dao.BookDAO;
import dao.BookDAO_JDBC;
import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

public class Test03 {
	public static void main(String[] args) {
		
		BookDAO dao = new BookDAO_Mariadb();
		BookService service = new BookServiceImpl(dao);
		
		BookVO vo = new BookVO();
		vo.setTitle("연습중1");
		vo.setPrice(900);
		vo.setPublisher("명지대");
		
		//service.bookAdd(vo);
		
		System.out.println("===============");
		System.out.println(service.getBook(6));
		
		System.out.println("================");
		service.deleteBook(6);
		service.bookList().forEach(i->{
			System.out.println(i);
		});

		System.out.println("=== 수정 ====");
		BookVO book = service.getBook(7);
		book.setPrice((int)(book.getPrice()*0.9));
		service.updateBook(book);
		service.bookList().forEach(i->{
			System.out.println(i);
		});
		System.out.println("=== 검색 ====");
 
		List<BookVO> list1 = service.searchBook("publisher","한");
		List<BookVO> list2 = service.searchBook("title","html");
		List<BookVO> list3 = service.searchBook("publisher","명지");
		
		list2.forEach(i->{System.out.println(i);});
	}
}
