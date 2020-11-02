package vo;

import java.io.Serializable;

public class BookVO implements Serializable{
	
	private int bookno; // NUMBER(4) PRIMARY KEY,
	private String title; // VARCHAR2(40),
	private String publisher; // VARCHAR2(40),
	private int price; // NUMBER(8)
	
	public BookVO() {	}

	public BookVO(int bookno, String title, String publisher, int price) {
		this.bookno = bookno;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
	}

	public int getBookno() {
		return bookno;
	}

	public void setBookno(int bookno) {
		this.bookno = bookno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookVO [bookno=" + bookno + ", title=" + title + ", publisher=" + publisher + ", price=" + price + "]";
	}

	
}
