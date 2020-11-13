package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO;
import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

class BookTest {

	BookDAO dao = new BookDAO_Mariadb();
	BookService service = new BookServiceImpl(dao);
	
	@BeforeEach
	void setUp() throws Exception {
		dao = new BookDAO_Mariadb();
		service = new BookServiceImpl(dao);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

//	@Test
	void add() throws Exception{
		BookVO vo = new BookVO();
		vo.setTitle("spring");
		vo.setPrice(2900);
		vo.setPublisher("...출판");
		
		service.bookAdd(vo);
		
	}
	
//	@Test
	void list() {
		System.out.println("== list ===");
		service.bookList().forEach(i->{
			System.out.println(i);
		});
	}
//	@Test
	void get() {
		System.out.println("== get ===");

		System.out.println(service.getBook(12));

	}
//	@Test
	void update() {
		System.out.println("=== 수정 ====");
		BookVO book = service.getBook(12);
		System.out.println(book);
		book.setPrice((int)(book.getPrice()*0.9));
		service.updateBook(book);
		System.out.println(service.getBook(12));
	}
	@Test
	void search() {
		System.out.println("=== 검색 ====");
		 
		List<BookVO> list1 = service.searchBook("publisher","한");
		List<BookVO> list2 = service.searchBook("title","html");
		List<BookVO> list3 = service.searchBook("publisher","명지");
		
		list2.forEach(i->{System.out.println(i);});
	}
}
