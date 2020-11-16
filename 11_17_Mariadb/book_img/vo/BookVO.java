package vo;

public class BookVO {
	private int bookno; // int(4) PRIMARY KEY auto_increment,
	private String title; // VARCHAR(40),
	private String publisher; // VARCHAR(40),
	private int price; // int(8)
	private String img; // VARCHAR(40),

	public BookVO() {	}

	
	public BookVO(int bookno, String title, String publisher, int price, String img) {
		super();
		this.bookno = bookno;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
		this.img = img;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
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
		return "BookVO [bookno=" + bookno + ", title=" + title + ", publisher=" + publisher + ", price=" + price
				+ ", img=" + img + "]";
	}

	
	
}
