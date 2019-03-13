package kr.or.bit.team1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.bit.team1.util.TeamFormat;

enum OrderStatus {
	ORDER, DISCOUNT, CANCEL, REFUND, PAYED
};

enum PayType {
	CASH, CARD
};

class Menu {
//	String name;
//	int price;
	HashMap <String, Integer> menu;

	public Menu(String name, int price) {
		menu=new HashMap<String, Integer>();
	}

	// 메뉴 추가
	public void addMenu(String name, Integer price) {// 이힘찬

	}

	/*
     * @method name : modifyMenu
     *
     * @date : 2019.03.12
     *
     * @author : 신지혁
     *
     * @description : 메뉴수정
     *
     * @parameters : String oldname, String name, Integer price
     *
     * @return : void
     */
	// 메뉴 수정
	public void modifyMenu(String oldname, String name, Integer price) {// 신지혁
		for(int i = 0; i<menu.size();i++) {
			if(menu.containsKey(oldname)) {
				System.out.println(menu);
				System.out.println("dddddd");
				menu.remove(oldname);
				menu.put(name, price);
				System.out.println(menu);
				break;
			}
		}
	}

	// 메뉴 삭제
	public void deleteMenu(String name) {// 권예지

	}

	
}

class Table {

	HashMap<Integer, OrderList> tables;
	Date date;
	boolean isPayed;

	public Table(int tableNo, OrderList orderList) {
		tables = new HashMap<Integer, OrderList>();
		tables.put(tableNo, orderList);
		this.date = new Date();
		this.isPayed = false;

	}

	public void showTable() {

	}

	// 테이블이동
	public void moveTable(int fromTable, int toTable) {// 강기훈
		OrderList temp = new OrderList();
		temp = tables.get(fromTable);
		tables.put(toTable, temp);
		tables.remove(fromTable);
	}

	// 테이블주문합치기
	public void mergeTable(int fromTable, int toTable) {// 권예지

	}
	
	// 테이블 추가
	public void addTables() {// 권순조

	}

	// 테이블 삭제
	public void deleteTables() {// 강기훈

	}

}

// 중간에 담는 그릇이 필요
class OrderList {

	ArrayList<Orders> orderlist;
	Customers customer;

	public OrderList() {
		this.orderlist = new ArrayList<Orders>();
		this.customer = new Customers();
	}

	// 주문내역을 보여줌
	public void listOrders() {// 권순조
		// 메뉴명-단가-수량-금액
	}

	/*
     * @method name : addOrder
     *
     * @date : 2019.03.12
     *
     * @author : 신지혁
     *
     * @description : 메뉴 주문
     *
     * @parameters : Orders order
     *
     * @return : void
     */
	
	// 주문
	public void addOrder(Orders order) { // Menu menu 신지혁
		orderlist.add(order);
	}


	// 선택취소
	public void deleteOrder(Orders order) { // 강기훈
		for (int i = 0; i < orderlist.size(); i++) {
			if (orderlist.get(i).orderId.equals(order.orderId)) {
				orderlist.remove(i);
			}
		}
	}
	/*
     * @method name : deleteOrderAll
     *
     * @date : 2019.03.12
     *
     * @author : 신지혁
     *
     * @description : 주문 전체취소
     *
     * @parameters :
     *
     * @return : void
     */
	
	// 전체취소
	public void deleteOrderAll() { // 신지혁
		orderlist.removeAll(orderlist);
	}

	// 에매한~ 수량변경
	public void changeQty(Menu menu, int qty) { // 일찬님

	}

	/*
	 * @method name : payCashAll
	 *
	 * @date : 2019.03.12
	 *
	 * @author : 권순조
	 *
	 * @description : 현금으로 결제 금액 전액 처리한다.
	 *
	 * @parameters : int amount
	 *
	 * @return : void
	 */

	// 전부 현금결제 // 결제와 오더리스트의 연결이 애메함
	public void payCashAll(int amount) {// 권순조 받은 현금이 물건의 총합보다 높으면 사용
		int exchange = 0;// 거스름돈을 저장할 공간 선언
		exchange = amount - orderSum();// 받을금액, 받은금액, 거스름돈
		System.out.println(exchange);

		// 시재액과 연결이 미흡
		// 테이블 초기화
		// 영수증출력
	}

	// 전부 카드결제
	public void payCardAll() { // 이힘찬
		// 받은금액
		// 테이블초기화
		// 영수증출력
	}

	/*
	 * @method name : payCash
	 *
	 * @date : 2019.03.12
	 *
	 * @author : 권순조
	 *
	 * @description : 현금으로 결제 금액을 처리한다.
	 *
	 * @parameters : int no, int amount > int amount
	 *
	 * @return : int
	 */

	public int payCash(int amount) {// 권순조

		int result = 0;// 현금을 한번 계산하고 남은 금액을 저장할 공간
		int sum = 0; // 구매한 물품의 총합을 구하는 공간 선언
		result = orderSum() - amount;// 물건의 총합에서 받은 현금을 馨 남은 것을 결과로 저장
		return result; // 리턴후 payDividieAmount에서 사용
	}

	/*
	 * @method name : payCard
	 *
	 * @date : 2019.03.12
	 *
	 * @author : 권순조
	 *
	 * @description : 현금으로 결제 금액을 처리한다.
	 *
	 * @parameters : int no, int amount > int amount
	 *
	 * @return : int
	 */

	public int payCard(int amount) {// 권예지

		int result = 0;// 현금을 한번 계산하고 남은 금액을 저장할 공간
		result = orderSum() - amount;// 물건의 총합에서 받은 현금을 馨 남은 것을 결과로 저장
		return result; // 리턴후 payDividieAmount에서 사용
	}

	public void payDivideAmount(int no, int amount) {// 일찬님
		if (no == 3) {
			do {
				System.out.println("결재 방식을 선택하세요");
				Scanner sc = new Scanner(System.in);
				int select = Integer.parseInt(sc.nextLine());
				if (select == 1) {// 1을
					System.out.println("현금 결재를 선택하셨습니다.");
					payCash(amount);

				} else if (select == 2) {
					System.out.println("카드 결재를 선택하셨습니다.");
					payCard(amount);
				}
			} while (payCard(amount) == 0 || payCash(amount) == 0);
		}
	}

//	print receipt
	public void printReceipt() {// 권예지

	}

	// 회원등록
	public void addMembers(Customers customers) {// 신지혁
		
	}

	// 포인트 적립
	public void addPoints(Customers customers, String phoneNumber, int amount) {// 강기훈
		int currPoint = customers.customer.get(phoneNumber);
		customers.customer.put(phoneNumber, (int) (currPoint + amount * 0.05));
	}

	// 포인트 사용
	public void usePoints(Customers customers, String phoneNumber) {// 힘찬이

	}

	/*
	 * @method name : orderSum
	 *
	 * @date : 2019.03.12
	 *
	 * @author : 권순조
	 *
	 * @description : 구매한 물품 금액의 합계를 구한다.
	 *
	 * @parameters :
	 *
	 * @return : int
	 */
	public int orderSum() {
		int sum = 0; // 구매한 물품의 총합을 구하는 공간 선언
//		for (int i = 0; i < orderlist.size(); i++) {// 구매한 물품의 총합을 구하는 포문
//			Orders order = orderlist.get(i);
//			sum += order.menuItem.price; // sum에 저장
//		}
		return sum;// 합계를 반환
	}

	@Override
	public String toString() {
		return "OrderList [orderlist=" + orderlist + ", customer=" + customer + "]";
	}
}

class Orders {

	static Long orderId = 0L;
	Date orderDate;
	Menu menuItem;
	Payments payment;

	public Orders(Menu menuItem) {
		orderId++;
		this.orderDate = new Date();
		this.menuItem = menuItem;
		this.payment = new CashPayments(); // initalization
	}

	public Orders(Menu menuItem, Payments payment) {
		orderId++;
		this.orderDate = new Date();
		this.menuItem = menuItem;
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Orders [orderDate=" + orderDate + ", menuItem=" + menuItem + ", payment=" + payment + "]";
	}

}

interface Payments {
//	현금
//	카드
//	분할계산
	public void pay();

}

class CashPayments implements Payments {

	@Override
	public void pay() { // 일찬님
		System.out.println(PayType.CASH);
	}

	// 포인트 적립
	public void addPoints(Customers customers, String phoneNumber, int amount) { // 이힘찬

	}

	// 포인트 사용
	public void usePoints(Customers customers, String phoneNumber, int amount) {// 권순조

	}

}

class CardPayments implements Payments {

	@Override
	public void pay() {// 신지혁
		System.out.println(PayType.CARD);
	}

	// 포인트 적립
	public void addPoints(Customers customers, String phoneNumber) {// 권예지

	}

	// 포인트 사용
	public void usePoints(Customers customers, String phoneNumber) {// 강기훈

	}

}

class Customers {
	HashMap<String, Integer> customer;// 키값: 전화번호,
	// 밸류값: 포인트

	public Customers() {
		this.customer = new HashMap<String, Integer>();
	}

	// 고객 추가
	public void addCustomers(String phoneNumber) {// 권순조
		Scanner sc = new Scanner(System.in);
		String PhonNum = sc.nextLine();
		customer.put(PhonNum, 0);

	}

	/*
	 * @method name : modifyCustomers
	 *
	 * @date : 2019.03.12
	 *
	 * @author : 정일찬
	 *
	 * @description : 고객정보를 수정한다.
	 *
	 * @parameters : String oldPhoneNumber, String phoneNumber
	 *
	 * @return : void
	 */
	public void modifyCustomers(String oldPhoneNumber, String phoneNumber) {
		if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // 핸드폰 정규표현식
			if (customer.containsKey(oldPhoneNumber)) {
				customer.put(phoneNumber, customer.get(oldPhoneNumber)); // 포인트를 새로운 핸드폰으로 옮김
				customer.remove(oldPhoneNumber); // 기존 폰넘버 삭제
			}
		} else {
			System.out.println("핸드폰번호를 확인하고 입력하세요");
		}
	}

	/*
     * @method name : findCustomers
     *
     * @date : 2019.03.12
     *
     * @author : 신지혁
     *
     * @description : 고객조회
     *
     * @parameters : String phoneNumber
     *
     * @return : void
     */
	// 고객 조회
	public void findCustomers(String phoneNumber) { // 신지혁 
		if(customer.get(phoneNumber)!=null)
			System.out.println(phoneNumber + "의 포인트는 : " + customer.get(phoneNumber) + "원 입니다");
		else
			System.out.println("고객이아닙니다");
	}

	// 고객 탈퇴
	public void deleCustomers(String phoneNumber) { // 이힘찬

	}

	// 고객 현황
	public void listCustomers() {// 강기훈
		for (Map.Entry<String, Integer> obj : customer.entrySet()) {
			System.out.println("전화번호:" + obj.getKey() + "/ Point:" + obj.getValue());

		}

	}
}

class Pos {

	Scanner sc = new Scanner(System.in);

	// log 저장디렉토리
	String logPath = "C:\\temp\\log";

	// 시재금액
	Integer amount;
	List<Orders> orders = new ArrayList<Orders>();
	OrderList orderList;

	// 판매관리, 매출관리, 회원관리, 메뉴관리, 테이블관리, 시스템 종료

	// 주문(테이블)
	public void orderTable(Integer tableNo, Menu menu) { // 일찬님
		// 테이블에 order add
	}

	// 결제 (테이블)
	public void payTableCash(Integer tableNo, Integer amount) {// 이힘찬
		// 테이블에서 order를 하나씩 가져와서 결제함
	}

	public void payTableCard(Integer tableNo) {// 권순조
		// 테이블에서 order를 하나씩 가져와서 결제함
	}

	// 결제 (테이블)
	public void payTableCardAll(Integer tableNo) { // 일찬님
		// 테이블의 order를 한가지 결제형식으로
	}

	public void payTableCashAll(Integer tableNo, Integer amount) {// 이힘찬
		// 테이블의 order를 한가지 결제형식으로
	}

	List<Menu> menuItem = new ArrayList<Menu>();

	// 메뉴관리

	// 테이블관리
	List<Table> tables = new ArrayList<Table>();


	// 고객관리
	Customers customers = new Customers();

	// 고객가입

	/*
     * @method name : addMembers
     *
     * @date : 2019.03.12
     *
     * @author : 신지혁
     *
     * @description : 회원등록호출
     *
     * @parameters : String phoneNumber
     *
     * @return : void
     */
	// 회원등록
	public void addMembers(String phoneNumber) {// 신지혁
		customers.addCustomers(phoneNumber);
	}
	// 고객조회
	// 고객탈퇴
	// 고객현황

	// overloading
//	public void deleCustomers(String name) {
//		
//	}

	// 현금관리
	public void cashAdjustment() { // 이힘찬
		// 현금시재액을 보여준다

	}

	// 매출
	// 메뉴별 매출 (일별)
	public void printSalesMenu(String date) { // 강기훈
		// 메뉴-수량-금액
	}

	// 결제별 매출 (일별)
	public void printSalesPayment(String date) { // 신지혁
		// 메뉴-카드(현금)-수량-금액
	}

	// 엑셀 export (메뉴별,결제별 매출)
	public void exportToExcel(String salesType) {// 일찬님

	}

	// 데이터 저장 (시스템 종료시 데이터 저장)
	public void save(String date) { // 권예지

	}

	// 데이터 로드 (시스템 시작시 데이터 로드)
	public void load(String date) {// 권예지

	}
	// 일별 log // method마다 util에서 정의된 것을 사용
}

public class Pos_System {
	public static void main(String[] args) {
		List<Menu> menuItem = new ArrayList<Menu>();
		menuItem.add(new Menu("짜장", 5000));
		menuItem.add(new Menu("짬뽕", 6000));
		menuItem.add(new Menu("우동", 5500));


		Orders order = new Orders(menuItem.get(1));
		Orders order2 = new Orders(menuItem.get(0));
		// System.out.println(order.toString());

		OrderList orderList = new OrderList();
		orderList.orderlist.add(order);
		orderList.orderlist.add(order2);
		orderList.customer = new Customers();
		System.out.println(orderList.toString());

		System.out.println(TeamFormat.dateTimeFormat(new Date()));
	}
}