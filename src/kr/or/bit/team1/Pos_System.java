package kr.or.bit.team1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



interface Payments {
//	현금
//	카드
//	분할계산
	public void pay();
		
}

class Menu {
	String name;
	int price;
}



class Orders {
	static Long orderId;
	Date orderDate;
	Menu	menuItem;
	Payments payment;
} 
class Table {
	
	HashMap<Integer, OrderList> tables= new HashMap<Integer, OrderList>();
	Date date;
	boolean isPayed;
	
	
	public Table(int tableNo,OrderList orderList){
		tables.put(tableNo, orderList);
		this.date = new Date();
		this.isPayed = false;
	
	}
	
	
public void showTable() {
		
		
	}
	
	//테이블이동
	public void moveTable(int fromTable, int toTable) {// 강기훈 
	 OrderList temp = new OrderList();
	 temp = tables.get(fromTable);
	 tables.put(toTable, temp);
	 tables.remove(fromTable);
	}
	//테이블주문합치기
	public void mergeTable(int fromTable, int toTable) {//권예지 
		
	}
}
class Customers {
	HashMap<String,Integer> customer = new HashMap<String,Integer>();// 키값: 전화번호, 밸류값: 포인트 

	//고객 현황 
public void listCustomers() {// 강기훈 
	  for( Map.Entry<String, Integer> obj: customer.entrySet()) {
		  System.out.println("전화번호:"+obj.getKey()+"/ Point:" +obj.getValue());
		  
	  }
	     
	}
	
}

class OrderList {
	List<Orders> orderlist = new ArrayList<Orders>();
	Customers customer;
	

	//선택취소
	public void deleteOrder(Orders order) { // 강기훈 
       for(int i=0; i<orderlist.size();i++) {
    	   if(orderlist.get(i).orderId.equals(order.orderId)) {
    		   orderlist.remove(i);
    	   }
       }
		
	}

	//포인트 적립
	public void addPoints(Customers customers, String phoneNumber, int amount) {// 강기훈
		int currPoint = customers.customer.get(phoneNumber);
		customers.customer.put(phoneNumber,(int)(currPoint+amount*0.05));
	}
}
class Pos {
	
	Scanner sc = new Scanner(System.in);
	
	//log 저장디렉토리
	String logPath = "C:\\temp\\log";
	
	// 시재금액
	Integer amount;
	List<Orders> orders = new ArrayList<Orders>();
	OrderList orderList;
	
	// 판매관리, 매출관리, 회원관리, 메뉴관리, 테이블관리, 시스템 종료
	
	List<Table> tables = new ArrayList<Table>();
	// 테이블 삭제
		public void deleteTables() {//강기훈 
		for(int i =0; i<tables.size();i++) {
			if(tables.get(i).isPayed) {
				tables.remove(i);
			}
		}	
		}
		// 고객관리
		Customers customers = new Customers(); 
		
		// 매출
		// 메뉴별 매출 (일별)
		public void printSalesMenu(String date) { //강기훈 
			// 메뉴-수량-금액
		}
	
	
}
class CardPayments implements Payments {

	@Override
	public void pay() {// 신지혁 
		System.out.println();
	}
	
	//포인트 적립
	public void addPoints(Customers customers, String phoneNumber) {// 권예지 
		
	}
	//포인트 사용
	public void usePoints(Customers customers, String phoneNumber) {// 강기훈 
		
	}

}

public class Pos_System {
	static Scanner sc = new Scanner(System.in);
	
	 public void displayMenu() {
		  int menuNum = 0;
		 
		  System.out.println("=========== POS ============");
		  System.out.println("1.판매관리");
		  System.out.println("2.매출관리");
		  System.out.println("3.회원관리");
		  System.out.println("4.메뉴관리");
		  System.out.println("5.테이블관리");
		  System.out.println("6.시스템종료");
		  System.out.println("원하시는 메뉴를 선택하세요: ");
		  menuNum = Integer.parseInt(sc.nextLine());
		  
		 
		 
	 }
	

	public static void main(String[] args) {
	
		
		
		
		
	}

}
