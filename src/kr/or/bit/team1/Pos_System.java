package kr.or.bit.team1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kr.or.bit.team1.util.TeamFormat;
import kr.or.bit.team1.util.TeamLogger;

enum OrderStatus {
	ORDER, DISCOUNT, CANCEL, REFUND, PAYED
};

enum PayType {
	CASH, CARD
};

class Table implements Serializable {

	HashMap<Integer, Bucket> tables;
	Date date;
	boolean isPayed; // @ deprecated

	// 정일찬 : 생성자변경
	public Table() {
		TeamLogger.info("Table()");
		tables = new HashMap<Integer, Bucket>();
		this.date = new Date();
		this.isPayed = false;

	}

	public void showTable() { // 정일찬

	}

	// 정일찬 :
	// addOrdetList 추가
	public void addOrderList(Integer tableNo, Bucket orderList) {
		TeamLogger.info("addOrderList(Integer tableNo, OrderList orderList)");
		tables.put(tableNo, orderList);
	}

	// 테이블 이동
	// FIX
	public void moveTable(int fromTable, int toTable) {// 강기훈
		TeamLogger.info("moveTable(int fromTable, int toTable)");
		Bucket temp = new Bucket();
		temp = tables.get(fromTable);
		tables.put(toTable, temp);
		tables.remove(fromTable);
	}

	// 테이블 주문 합치기
	public void mergeTable(int fromTable, int toTable) {// 권예지
		Bucket temp = new Bucket();
		temp = tables.get(fromTable);
		Bucket temp2 = new Bucket();
		temp2 = tables.get(toTable);

		for (int i = 0; i < temp2.orderlist.size(); i++)
			temp.orderlist.add(temp2.orderlist.get(i));

	}

	// 테이블 추가
	// 정일찬 OrderList orderList parameter 삭제
	public void addTable(int tableNo) {// 권순조
		TeamLogger.info("addTable(int tableNo)");
		this.tables.put(tableNo, null);

	}

	// 테이블 삭제
	// 정일찬 int tableNo parameter 추가
	// FIX
	public void deleteTable(int tableNo) {// 강기훈
		for (Map.Entry<Integer, Bucket> obj : tables.entrySet()) {
			if (obj.getValue().isPayed) {
				tables.put(obj.getKey(), new Bucket());
			}
		}
	}

}


class Bucket implements Serializable {

	ArrayList<Orders> orderlist;
	Customers customer;
	boolean isPayed;

	public Bucket() {
		this.orderlist = new ArrayList<Orders>();
		this.customer = null;
		this.isPayed = false;
	}

	// 주문내역을 보여줌
	public void listOrders() {// 권순조
		List<String> menuSet = new ArrayList<String>();

		for (int i = 0; i < orderlist.size(); i++) {
			String name = orderlist.get(i).menuItem.name;
			menuSet.add(name);
			System.out.println(menuSet.toString());
			for (int j = 0; j < menuSet.size(); j++) {
				if (name.equals(menuSet.get(j))) {
					continue;
				} else {
					int price = orderlist.get(i).menuItem.price;
					int quty = menuQty(orderlist.get(i).menuItem);
					int bill = price * quty;
					System.out.printf("메뉴: %s        단가 : %d   수량 : %d  금액 : %d \n", name, price, quty, bill);
				}
			}
		}
	}

	/*
	 * @method name : addOrder
	 *
	 * @date : 2019.03.12
	 *
	 * @author : 신지혁
	 *
	 * @description : order를 추가한다.
	 *
	 * @parameters : Orders order
	 *
	 * @return : void
	 */
	public void addOrder(Orders order) {
		TeamLogger.info("addOrder");
		orderlist.add(order);
	}

	/*
	 * @method name : deleteOrder
	 *
	 * @date : 2019.03.14
	 *
	 * @author : 강기훈
	 *
	 * @description : order를 제거한다.
	 *
	 * @parameters : Orders order
	 *
	 * @return : void
	 */
	public void deleteOrder(Orders order) { // 강기훈
		TeamLogger.info("deleteOrder(Orders order)");
		if (order != null) {
			changeQty(order.menuItem, -1);
		}
	}

	/*
	 * @method name : deleteOrderAll
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 신지혁
	 *
	 * @description : 전체 order를 제거한다.
	 *
	 * @parameters :
	 *
	 * @return : void
	 */
	public void deleteOrderAll() {
		TeamLogger.info("deleteOrderAll");
		int num = orderlist.size();
		orderlist.removeAll(orderlist);
	}

	/*
	 * @method name : changeQty
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 신지혁
	 *
	 * @description : 전체 order를 제거한다.
	 *
	 * @parameters : Menu menu, int qty
	 *
	 * @return : void
	 * 
	 * @example
	 *  해당 메뉴를 2개 추가하면 qty=2, 2개 제외하면 qty=-2
	 */
	public void changeQty(Menu menu, int qty) { 
		TeamLogger.info("changeQty(Menu menu, int qty)");
		if (qty < 0) { // 주문취소

			int orderedQty = menuQty(menu);
			int canceledQty = Math.abs(qty);
			if (canceledQty <= orderedQty) {
				while (canceledQty > 0) {
					if (orderlist.indexOf(getOrder(menu)) != -1) {
						orderlist.remove(orderlist.indexOf(getOrder(menu)));
					}
					canceledQty--;
				}
			} else {
				System.out.println("주문수보다 많은 갯수를  취소했습니다.");
			}
		} else if (qty > 0) { // 주문추가
			for (int i = 1; i <= qty; i++) {
				orderlist.add(new Orders(menu));
			}
		}

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
	// ADD System.out.println("고객명을 입력하세요");
	// FIX
	public void payCashAll(int amount) {// 권순조 받은 현금이 물건의 총합보다 높으면 사용
		int exchange = 0;// 거스름돈을 저장할 공간 선언
		customer = new Customers();
		Scanner sc = new Scanner(System.in);
		int i = Integer.parseInt(sc.nextLine());

		System.out.println("포인트 사용 : 1, 포인트 미사용 :2");
		if (i == 1) {
			String s = sc.nextLine();
			int point = customer.customer.get(s);
			exchange = amount + point - orderSum();// 받을금액, 받은금액, 거스름돈
		} else if (i == 2) {
			exchange = amount - orderSum();
		}
		System.out.println(exchange);

		printReceipt();// 영수증 출력
	}

	// 전부 카드결제
	// FIX
	public void payCardAll() { // 이힘찬
		// 받은금액
		System.out.println("결제 금액 : " + orderSum());
		// 테이블초기화
		orderlist = null;
		// 영수증출력
		printReceipt();
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
	// ?
	public int payCash(int amount) {// 권순조

		int result = 0;// 현금을 한번 계산하고 남은 금액을 저장할 공간
		int sum = 0; // 구매한 물품의 총합을 구하는 공간 선언
		result = orderSum() - amount;// 물건의 총합에서 받은 현금을 馨 남은 것을 결과로 저장
		return result; // 리턴후 payDividieAmount에서 사용
	}

	// ?
	public int payCard(int amount) {// 권예지

		int result = 0;// 현금을 한번 계산하고 남은 금액을 저장할 공간
		result = orderSum() - amount;// 물건의 총합에서 받은 현금을 馨 남은 것을 결과로 저장
		return result; // 리턴후 payDividieAmount에서 사용
	}

	// FIX
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
	// ADJUST tf
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

	// 회원등록 @deprecated
	public void addMembers(Customers customers) {// 신지혁 @deprecated
	}

	// 포인트 적립
	public void addPoints(Customers customers, String phoneNumber, int amount) { // 권예지^^
		Bucket orderlist = new Bucket();
		int paypoint = customers.customer.get(phoneNumber);
		customers.customer.put(phoneNumber, (int) (paypoint + (orderlist.orderSum() * 0.05)));
	}

	// 포인트 사용
	// FIX customer를 받아야 함
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

	// FIX : 기존 customer객체를 가져와야 함
	public void isUsePoint() { // 권예지
		Scanner sc = new Scanner(System.in);
		System.out.println("포인트를 사용하시겠습니까?");
		String choice = sc.nextLine();// 포인트를 사용할지 확인하는 로직 시작
		if (choice.equals("Y")) {
			System.out.println("포인트사용 로직 시작합니다.");
			String phNum = sc.nextLine();
			usePoints(phNum);
		} else if (choice.equals("N")) {
			System.out.println("가입하시겠습니까? Y/N");
			String choice2 = sc.nextLine();
			if (choice.equals("Y")) {
				choice2 = sc.nextLine();
				Customers customers = new Customers();
				customers.addCustomers(choice2);
			} else if (choice.equals("N")) {
				System.out.println("결제 로직 시작합니다.");
			}
		}
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
		TeamLogger.info("orderSum");
		int sum = 0;
		for (int i = 0; i < orderlist.size(); i++) {
			Orders order = orderlist.get(i);
			sum += order.menuItem.price;
		}
		return sum;// 합계를 반환
	}

	/*
	 * @method name : menuQty
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 정일찬
	 *
	 * @description : 해당 메뉴별 수량을 반환
	 *
	 * @parameters : Menu menu
	 *
	 * @return : int
	 */

	public int menuQty(Menu menu) {
		TeamLogger.info("menuQty(Menu menu)");
		int qty = 0;
		for (int i = 0; i < this.orderlist.size(); i++) {
			if (orderlist.get(i).menuItem.name.trim().equalsIgnoreCase(menu.name.trim())) {
				qty++;
			}
		}
		return qty;
	}

	/*
	 * @method name : getOrder
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 정일찬
	 *
	 * @description : 해당 메뉴에 해당하는 order를 반환
	 *
	 * @parameters : Menu menu
	 *
	 * @return : Orders
	 */
	public Orders getOrder(Menu menu) {
		TeamLogger.info("getOrder");
		Orders order = null;
		for (int i = 0; i < this.orderlist.size(); i++) {
			if (orderlist.get(i).menuItem.equals(menu)) {
				order = orderlist.get(i);
			}
		}
		return order;
	}

	@Override
	public String toString() {
		return "OrderList [orderlist=" + orderlist + ", customer=" + customer + "]";
	}

}

class Pos implements Serializable {

	transient Scanner sc = new Scanner(System.in);

	// log 저장디렉토리
	String logPath = "C:\\temp\\log";

	// 시재금액
	static int amount = 200000;
	List<Orders> orders = new ArrayList<Orders>();
	Bucket orderList;
	Table tables = new Table();
	List<Menu> menuItem = new ArrayList<Menu>();
	Customers customers = new Customers();

	// log경로 설정
	Pos() {
		TeamLogger.getLogger(logPath);
		TeamLogger.info("POS System v0.1");
		TeamLogger.info("@copyleft TeamSoft 2019");

		// load(date);
	}

	public void viewTable(int seatCount) {

		for (int i = 1; i <= seatCount; i++) {
			System.out.printf("[%d]", i);
			if (i % 3 == 0) {
				System.out.println();
			}

		}

	}

	public void takeOrder() {
		int tableNum = 0;
		System.out.println("주문받을 테이블을 선택하세요! ");

	}

	void menuManage() {

		while (true) {
			System.out.println(menuItem.toString());

			int menuNum = 0;
			String menuName = null;
			String oldName = null;

			int menuPrice = 0;
			System.out.println("메뉴를 추가하거나 수정할 수 있습니다.");
			System.out.println("1.메뉴추가");
			System.out.println("2.메뉴수정");
			System.out.println("3.메뉴삭제");
			System.out.println("4.종료");
			System.out.println("원하는 번호를 입력하세요");

			menuNum = Integer.parseInt(sc.nextLine());
			switch (menuNum) {

			case 1:
				System.out.println("메뉴이름: ");
				menuName = sc.nextLine();

				System.out.println("가격:");
				menuPrice = Integer.parseInt(sc.nextLine());

				addMenu(menuName, menuPrice);
				break;
			case 2:
				System.out.println("수정할 메뉴를 입력하세요 ");
				oldName = sc.nextLine();

				System.out.println("새로운 메뉴를 입력하세요 ");
				menuName = sc.nextLine();

				System.out.println("새로운 가격을 입력하세요 ");
				menuPrice = Integer.parseInt(sc.nextLine());

				modifyMenu(oldName, menuName, menuPrice);
				break;

			case 3:
				System.out.println("삭제할 메뉴를 입력하세요: ");

				menuName = sc.nextLine();

				deleteMenu(menuName);
				break;

			case 4:
				posStart();

			default:
				System.out.println("다시 입력하세요");

			}

		}

	}

	public void posStart() {
		int menuNum = 0;
		System.out.println("POS SYSTEM");
		System.out.println("1.판매관리");
		System.out.println("2.매출관리");
		System.out.println("3.회원관리");
		System.out.println("4.메뉴관리");
		System.out.println("5.테이블관리");
		System.out.println("6.시스템종료");

		System.out.print("번호를 입력하세요:");
		menuNum = Integer.parseInt(sc.nextLine());

		switch (menuNum) {

		case 1:
			viewTable(9);
			break;
		case 4:
			menuManage();

			break;

		}

	}

	// 판매관리, 매출관리, 회원관리, 메뉴관리, 테이블관리, 시스템 종료

	// 주문(테이블)
	public void orderTable(Integer tableNo, Menu menu) { // 일찬님
		// 테이블에 order add
	}

	public void payTableCash(Integer tableNo) {// 이힘찬
		// 테이블에서 order를 하나씩 가져와서 결제함
		for (int i = 0; i < tables.tables.get(tableNo).orderlist.size(); i++) {
			String menu = tables.tables.get(tableNo).orderlist.get(i).menuItem.name; // 메뉴이름
			int price = tables.tables.get(tableNo).orderlist.get(i).menuItem.price; // 가격
			System.out.print(menu + ", " + price + "원 입니다~ 돈을 입력하세요 : ");
			int amount = sc.nextInt();
			if (amount < price) {
				System.out.println("금액이 부족합니다");
				i--;
				break;
			} else {
				System.out.println("받은돈 : " + amount);
				System.out.println("잔돈 : " + (amount - price));
			}
		}
	}

	public void payTableCard(Integer tableNo) {// 권순조
		// 테이블에서 order를 하나씩 가져와서 결제함
	}

	// 결제 (테이블)
	public void payTableCardAll(Integer tableNo) { // 일찬님
		// 테이블의 order를 한가지 결제형식으로
	}

	public void payTableCashAll(Integer tableNo, Integer amount) {// 이힘찬
		int price = tables.tables.get(tableNo).orderSum(); // 가격
		int change = amount - price; // 잔돈
		System.out.println("받은돈 : amount");
		System.out.println("잔   돈 : change");
	}

	// 메뉴관리
	// 메뉴 추가
	public void addMenu(String name, Integer price) {// 이힘찬
		menuItem.add(new Menu(name, price));
	}

	// 메뉴 수정
	public void modifyMenu(String oldname, String name, Integer price) {// 신지혁
		for (int i = 0; i < menuItem.size(); i++) {
			if (menuItem.get(i).name.equals(oldname)) {
				menuItem.remove(i);
				menuItem.add(new Menu(name, price));
				break;
			}
		}
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
		System.out.println("현금시재액 : " + amount);
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

	// 데이터 저장
	public void save(Object object, String pathFile) { // 권예지
	}

	// 데이터 로드 (시스템 시작시 데이터 로드)
	public void load(Object object, String pathFile) {// 권예지

	}
}

public class Pos_System {
	public static void main(String[] args) {
		Pos pos = new Pos();

//		// 데이터 로드 (시스템 시작시 데이터 로드)
//		String pathFile = "C:\\Temp\\pos.obj";
//		pos=(Pos)TeamFiles.loadObject(pathFile);
//		
//		
//		pos.posStart();
//
//		// 데이터 저장 (시스템 종료시 데이터 저장)
//		TeamFiles.saveObject(pos, "pathFile");

		// ========================
		// 이하 테스트용도

		Customers client = new Customers();
		client.addCustomers("010-1111-1111");
		client.addCustomers("010-2222-2222");
		client.addCustomers("010-3333-3333");
		client.deleCustomers("010-1111-2222");
		client.findCustomers("010-2222-1111");

		System.out.println(client.toString());

		pos.addMenu("짜장", 5000);
		pos.addMenu("짬뽕", 6000);
		pos.addMenu("우동", 5500);

		Menu pickMenu = pos.getMenu("짜장");

		System.out.println(pickMenu.toString());

		// Order 생성
		Orders order1 = new Orders(pos.getMenu("짜장"));
		Orders order2 = new Orders(pos.getMenu("짬뽕"));
		Orders order3 = new Orders(pos.getMenu("짬뽕"));

		System.out.println(order1.toString());

		// OrderList 생성
		Bucket orderList = new Bucket();
		orderList.addOrder(order1);
		orderList.addOrder(order2);
		orderList.addOrder(order3);

		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));

		System.out.println("짜장 5개 추가");
		orderList.changeQty(pos.getMenu("짜장"), 5);

		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));

		System.out.println("짜장 6개 취소");
		orderList.changeQty(pos.getMenu("짜장"), -6);

		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));

		System.out.println("우동 3개 추가");
		orderList.changeQty(pos.getMenu("우동"), 3);

		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
		System.out.println("우동 주문수 : " + orderList.menuQty(pos.getMenu("우동")));

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
		Bucket afterLunch = tables.tables.get(1);
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
