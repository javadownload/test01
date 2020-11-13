package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO_Mariadb;
import service.BookService;
import service.BookServiceImpl;

class BookTest {

	BookService service = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		BookDAO_Mariadb dao = new BookDAO_Mariadb();
		service = new BookServiceImpl(dao);
		
	}

	@Test
	void list() {
		service.bookList().forEach(i->{System.out.println(i);});
	}
	
	//@Test
	void add() {
		
	}
	//@Test
	void delete() {
		
	}
	//@Test
	void update() {
		
	}
	//@Test
	void search() {
		
	}
	
}
