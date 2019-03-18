package kr.or.bit.team1;

public class Sales {
	private String  date;
	private String  	menuName;
    private int  	price;   
    private int    	qty;    
    private int    	sales;
    
	public Sales(String date, String menuName, int price, int qty, int sales) {
		this.date = date;
		this.menuName = menuName;
		this.price = price;
		this.qty = qty;
		this.sales = sales;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Sales [date=" + date + ", menuName=" + menuName + ", price=" + price + ", qty=" + qty + ", sales="
				+ sales + "]";
	}  
    
}