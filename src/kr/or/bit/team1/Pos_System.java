package kr.or.bit.team1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kr.or.bit.team1.util.TeamFormat;

enum OrderStatus {
	ORDER, DISCOUNT, CANCEL, REFUND, PAYED
};

enum PayType {
	CASH, CARD
};

class Menu {
	String name;
	int price;
//	HashMap <String, Integer> menu;

//	public Menu() {
//		menu=new HashMap<String, Integer>();
//	}
	public Menu(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", price=" + price + "]";
	}
}

class Table {

	HashMap<Integer, OrderList> tables;
	Date date;
	boolean isPayed;

	// 정일찬 : 생성자변경
	public Table() {
		tables = new HashMap<Integer, OrderList>();
		this.date = new Date();
		this.isPayed = false;

	}

	public void showTable() {

	}

	// 정일찬 :
	// addOrdetList 추가
	public void addOrderList(Integer tableNo, OrderList orderList) {
		// 테이블에 order add
		tables.put(tableNo, orderList);
	}

//	// 결제 (테이블)
//	public void payTableCash(Integer tableNo, Integer amount) {// 이힘찬
//		// 테이블에서 order를 하나씩 가져와서 결제함
//	}
//
//	public void payTableCard(Integer tableNo) {// 권순조
//		// 테이블에서 order를 하나씩 가져와서 결제함
//	}
//
//	// 결제 (테이블)
//	public void payTableCardAll(Integer tableNo) { // 일찬님
//		// 테이블의 order를 한가지 결제형식으로
//	}
//
//	public void payTableCashAll(Integer tableNo, Integer amount) {// 이힘찬
//		// 테이블의 order를 한가지 결제형식으로
//	}

	// 테이블이동
	public void moveTable(int fromTable, int toTable) {// 강기훈
		OrderList temp = new OrderList();
		temp = tables.get(fromTable);
		tables.put(toTable, temp);
		tables.remove(fromTable);
	}

	// 테이블주문합치기
	public void mergeTable(int fromTable, int toTable) {// 권예지

		OrderList temp = new OrderList();
		temp = tables.get(fromTable);
		OrderList temp2 = new OrderList();
		temp2 = tables.get(toTable);

		for (int i = 0; i < temp2.orderlist.size(); i++)
			temp.orderlist.add(temp2.orderlist.get(i));

	}

	// 테이블 추가
	// 정일찬 OrderList orderList parameter 삭제
	public void addTable(int tableNo) {// 권순조
		this.tables.put(tableNo, null);

	}

	// 테이블 삭제
	// 정일찬 int tableNo parameter 추가
	public void deleteTable(int tableNo) {// 강기훈
		for (Map.Entry<Integer, OrderList> obj : tables.entrySet()) {
			if (obj.getValue().isPayed) {
				tables.put(obj.getKey(), new OrderList());
			}
		}
	}

}

// 중간에 담는 그릇이 필요
class OrderList {

	ArrayList<Orders> orderlist;
	Customers customer;
	// 강기훈 : isPayed추가
	boolean isPayed;

	public OrderList() {
		this.orderlist = new ArrayList<Orders>();
		this.customer = null;
		this.isPayed = false;
	}

	// 주문내역을 보여줌
	public void listOrders() {// 권순조
		// 메뉴명-단가-수량-금액
	}

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

	// 전체취소
	public void deleteOrderAll() { // 신지혁

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

	// print receipt
	public void printReceipt() {// 권예지, 파라메터에table의주솟값을받게바꿔야할것같습니다..
		TeamFormat tf = new TeamFormat();
		Pos pos = new Pos();
		Table t = new Table();
		CardPayments cp = new CardPayments();
		int pay = 0;

		PayType paytype = null;
		System.out.println("테이블번호: " + t.tables.get(0) + "\n");
		System.out.println("거래일시: " + tf.dateTimeFormat(t.date));
		System.out.println("거래유형: " + paytype);
		System.out.println("할부기간: 일시불");
		System.out.println("=====================================");
		System.out.println("메뉴이름\t\t단가\t수량\t금액\t");
		System.out.println("=====================================");
		listOrders();
		System.out.println("=====================================");
		System.out.println("총합계: " + orderSum());
		System.out.println("받은돈: " + pay);
		System.out.println("\t\t\t\t적립포인트: ");

	}

	// 포인트 적립
	public void addPoints(Customers customers, String phoneNumber, int amount) { // 권예지^^
		OrderList orderlist = new OrderList();
		int paypoint = customers.customer.get(phoneNumber);
		customers.customer.put(phoneNumber, (int) (paypoint + (orderlist.orderSum() * 0.05)));
	}

	// 포인트 사용
	public int usePoints(String phoneNumber) {// 권예지^^
		int usePointsResult = 0;
		int result = 0;
		Customers cus = new Customers();
		Scanner sc = new Scanner(System.in);
		if (!cus.customer.containsKey(phoneNumber)) {
			System.out.println("등록된 회원 번호가 없습니다.");
			System.out.println("가입하시겠습니까?");
			System.out.println("가입 : 1, 취소 : 2");
			String st = sc.nextLine();
			if (st == "1") {
				System.out.println("가입을 진행합니다. 번호를 입력해주세요");
				st = sc.nextLine();
				cus.addCustomers(st);
			} else if (st == "2") {
				System.out.println("포인트 사용을 취소합니다.");
			}
		} else {
			usePointsResult = cus.customer.get(phoneNumber);
			cus.customer.put(phoneNumber, 0);
		}
		return usePointsResult;
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
		this.payment = null; // initalization
	}

	public Orders(Menu menuItem, Payments payment) {
		orderId++;
		this.orderDate = new Date();
		this.menuItem = menuItem;
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Orders [orderDate=" + TeamFormat.dateTimeFormat(orderDate) + ", menuItem=" + menuItem + ", payment="
				+ payment + "]";
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

}

class CardPayments implements Payments {

	@Override
	public void pay() {// 신지혁
		System.out.println(PayType.CARD);
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
//		Scanner sc = new Scanner(System.in);
//		String PhonNum = sc.nextLine();
		if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
			customer.put(phoneNumber, 0);
		}

	}

	// 회원등록 OrderList클래스에서 이동받음..
	public void addMembers(String phoneNumber) {// 신지혁
		customer.put(phoneNumber, 0);
		System.out.println(phoneNumber + "추가 완료");
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

	// 고객 조회
	public void findCustomers(String phoneNumber) { // 신지혁
		if (customer.get(phoneNumber) != null)
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

	@Override
	public String toString() {
		return "Customers [customer=" + customer + "]";
	}

}

class Pos {

	Scanner sc = new Scanner(System.in);

	// log 저장디렉토리
	String logPath = "C:\\temp\\log";

	// 시재금액
	Integer amount = 200000;
	List<Orders> orders = new ArrayList<Orders>();
	OrderList orderList;
	Table tables = new Table();
	List<Menu> menuItem = new ArrayList<Menu>();
	Customers customers = new Customers();

	// 판매관리, 매출관리, 회원관리, 메뉴관리, 테이블관리, 시스템 종료

//	// 주문(테이블)
//	public void orderTable(Integer tableNo, Menu menu) { // 일찬님
//		// 테이블에 order add
//	}
//
//	// 결제 (테이블)
//	public void payTableCash(Integer tableNo, Integer amount) {// 이힘찬
//		// 테이블에서 order를 하나씩 가져와서 결제함
//	}
//
//	public void payTableCard(Integer tableNo) {// 권순조
//		// 테이블에서 order를 하나씩 가져와서 결제함
//	}
//
//	// 결제 (테이블)
//	public void payTableCardAll(Integer tableNo) { // 일찬님
//		// 테이블의 order를 한가지 결제형식으로
//	}
//
//	public void payTableCashAll(Integer tableNo, Integer amount) {// 이힘찬
//		
//	}

	// 메뉴관리
	// 메뉴 추가
	public void addMenu(String name, Integer price) {// 이힘찬
		menuItem.add(new Menu(name, price));
	}

	// 메뉴 수정
	public void modifyMenu(String oldname, String name, Integer price) {// 신지혁
	}

	// 메뉴 삭제
	public void deleteMenu(String name) {// 권예지
		menuItem.remove(name);
	}

	public Menu getMenu(String name) {
		Menu menu = null;
		for (Menu m : menuItem) {
			if (m.name.trim().equalsIgnoreCase(name)) {
				menu = m;
			}
		}
		return menu;

	}

	// 고객관리

	// 고객가입

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

	// 데이터저장(시스템종료시데이터저장)
	public void save(String date) { // 권예지
		Pos pos = new Pos();
		try {
			FileOutputStream fos = new FileOutputStream(logPath, true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(pos);
			oos.close();
			bos.close();
			fos.close();
			System.out.println(date + " 저장 완료");
		} catch (Exception e) {
		}
	}

	// 데이터로드(시스템시작시데이터로드)
	public void load(String date) {// 권예지
		Pos pos = new Pos();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(logPath);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			pos = (Pos) ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// 일별log // method마다util에서정의된것을사용
}

public class Pos_System {
	public static void main(String[] args) {

		Pos pos = new Pos();
		pos.addMenu("짜장", 5000);
		pos.addMenu("짬뽕", 6000);
		pos.addMenu("우동", 5500);

		Menu pickMenu = pos.getMenu("짜장");

		System.out.println(pickMenu.toString());

		// Order 생성
		Orders order1 = new Orders(pos.getMenu("짜장"));
		Orders order2 = new Orders(pos.getMenu("짬뽕"));

		System.out.println(order1.toString());

		// OrderList 생성
		OrderList orderList = new OrderList();
		orderList.addOrder(order1);
		orderList.addOrder(order2);
		System.out.println(orderList.toString());

		// Table
		Table tables = new Table();

		// add table
		tables.addTable(1);
		tables.addTable(2);
		tables.addTable(3);
		tables.addTable(4);
		System.out.println(tables.tables.toString());
		// add OrderList to Table
		tables.addOrderList(1, orderList);
		System.out.println(tables.tables.toString());

		// 결제
		Customers sonnom = new Customers();
		sonnom.addCustomers("010-2222-3333");
		System.out.println("손님 : " + sonnom.customer.toString());

		int yourbill = 20000;
		// cash
		OrderList afterLunch = tables.tables.get(1);
		for (int i = 0; i < afterLunch.orderlist.size(); i++) {
			afterLunch.orderlist.get(i).payment = new CashPayments();
			yourbill -= afterLunch.orderlist.get(i).menuItem.price;
			// point 적립
			int new_point = sonnom.customer.get("010-2222-3333")
					+ (int) (afterLunch.orderlist.get(i).menuItem.price * 0.05);
			sonnom.customer.put("010-2222-3333", new_point);
			afterLunch.orderlist.get(i).payment.pay();

		}
		// 결제완료
		afterLunch.isPayed = true;
		System.out.println(yourbill);
		System.out.println("손님의 포인트 : " + sonnom.customer.toString());

	}
}