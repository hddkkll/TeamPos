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
		TeamLogger.info("HELL POS System v0.1");
		TeamLogger.info("@copyleft TeamSoft 2019");

		// load(date);
	}

	public int getQtyPerMenu(String date, Menu menu, PayType payType) {
		int qty = 0;
		for (int i = 0; i < this.orderList.orderlist.size(); i++) {
			if (TeamFormat.dateFormat(this.orderList.orderlist.get(i).orderDate).equalsIgnoreCase(date)) {
				if (orderList.orderlist.get(i).menuItem.name.trim().equalsIgnoreCase(menu.name.trim())) {
					if (orderList.orderlist.get(i).payment.getPayType().equals(payType)) {
						qty++;
					}
				}
			}
		}
		return qty;
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
//	public void viewTable(int seatCount) {
//
//		for (int i = 1; i <= seatCount; i++) {
//			System.out.printf("[%d]", i);
//			if (i % 3 == 0) {
//				System.out.println();
//			}
//
//		}
//
//	}
//
//	public void takeOrder() {
//		int tableNum = 0;
//		System.out.println("주문받을 테이블을 선택하세요! ");
//
//	}
//
//	void menuManage() {
//
//		while (true) {
//			System.out.println(menuItem.toString());
//
//			int menuNum = 0;
//			String menuName = null;
//			String oldName = null;
//
//			int menuPrice = 0;
//			System.out.println("메뉴를 추가하거나 수정할 수 있습니다.");
//			System.out.println("1.메뉴추가");
//			System.out.println("2.메뉴수정");
//			System.out.println("3.메뉴삭제");
//			System.out.println("4.종료");
//			System.out.println("원하는 번호를 입력하세요");
//
//			menuNum = Integer.parseInt(sc.nextLine());
//			switch (menuNum) {
//
//			case 1:
//				System.out.println("메뉴이름: ");
//				menuName = sc.nextLine();
//
//				System.out.println("가격:");
//				menuPrice = Integer.parseInt(sc.nextLine());
//
//				addMenu(menuName, menuPrice);
//				break;
//			case 2:
//				System.out.println("수정할 메뉴를 입력하세요 ");
//				oldName = sc.nextLine();
//
//				System.out.println("새로운 메뉴를 입력하세요 ");
//				menuName = sc.nextLine();
//
//				System.out.println("새로운 가격을 입력하세요 ");
//				menuPrice = Integer.parseInt(sc.nextLine());
//
//				modifyMenu(oldName, menuName, menuPrice);
//				break;
//
//			case 3:
//				System.out.println("삭제할 메뉴를 입력하세요: ");
//
//				menuName = sc.nextLine();
//
//				deleteMenu(menuName);
//				break;
//
//			case 4:
//				posStart();
//
//			default:
//				System.out.println("다시 입력하세요");
//
//			}
//
//		}
//
//	}
//
//	public void posStart() {
//		int menuNum = 0;
//		System.out.println("POS SYSTEM");
//		System.out.println("1.판매관리");
//		System.out.println("2.매출관리");
//		System.out.println("3.회원관리");
//		System.out.println("4.메뉴관리");
//		System.out.println("5.테이블관리");
//		System.out.println("6.시스템종료");
//
//		System.out.print("번호를 입력하세요:");
//		menuNum = Integer.parseInt(sc.nextLine());
//
//		switch (menuNum) {
//
//		case 1:
//			viewTable(9);
//			break;
//		case 4:
//			menuManage();
//
//			break;
//
//		}
//
//	}
//
//	// 판매관리, 매출관리, 회원관리, 메뉴관리, 테이블관리, 시스템 종료
//
//	// 주문(테이블)
//	public void orderTable(Integer tableNo, Menu menu) { // 일찬님
//		// 테이블에 order add
//	}
//
//	public void payTableCash(Integer tableNo) {// 이힘찬
//		// 테이블에서 order를 하나씩 가져와서 결제함
//		for (int i = 0; i < tables.tables.get(tableNo).orderlist.size(); i++) {
//			String menu = tables.tables.get(tableNo).orderlist.get(i).menuItem.name; // 메뉴이름
//			int price = tables.tables.get(tableNo).orderlist.get(i).menuItem.price; // 가격
//			System.out.print(menu + ", " + price + "원 입니다~ 돈을 입력하세요 : ");
//			int amount = sc.nextInt();
//			if (amount < price) {
//				System.out.println("금액이 부족합니다");
//				i--;
//				break;
//			} else {
//				System.out.println("받은돈 : " + amount);
//				System.out.println("잔돈 : " + (amount - price));
//			}
//		}
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
//		int price = tables.tables.get(tableNo).orderSum(); // 가격
//		int change = amount - price; // 잔돈
//		System.out.println("받은돈 : amount");
//		System.out.println("잔   돈 : change");
//	}
//
//	// 메뉴관리
//	// 메뉴 추가
//	public void addMenu(String name, Integer price) {// 이힘찬
//		menuItem.add(new Menu(name, price));
//	}
//
//	// 메뉴 수정
//	public void modifyMenu(String oldname, String name, Integer price) {// 신지혁
//		for (int i = 0; i < menuItem.size(); i++) {
//			if (menuItem.get(i).name.equals(oldname)) {
//				menuItem.remove(i);
//				menuItem.add(new Menu(name, price));
//				break;
//			}
//		}
//	}
//
//	// 메뉴 삭제
//	public void deleteMenu(String name) {// 권예지
//		menuItem.remove(name);
//	}
//
//	public Menu getMenu(String name) {
//		Menu menu = null;
//		for (Menu m : menuItem) {
//			if (m.name.trim().equalsIgnoreCase(name)) {
//				menu = m;
//			}
//		}
//		return menu;
//
//	}
//
//	// 고객관리
//
//	// 고객가입
//	public void addMembers(String phoneNumber) {// 신지혁
//		customers.addCustomers(phoneNumber);
//	}
//	// 고객조회
//	// 고객탈퇴
//	// 고객현황
//
//	// overloading
////	public void deleCustomers(String name) {
////		
////	}
//
//	// 현금관리
//	public void cashAdjustment() { // 이힘찬
//		// 현금시재액을 보여준다
//		System.out.println("현금시재액 : " + amount);
//	}
//
//	// 매출
//	// 메뉴별 매출 (일별)
//	public void printSalesMenu(String date) { // 강기훈
//		// 메뉴-수량-금액
//	}
//
//	// 결제별 매출 (일별)
//	public void printSalesPayment(String date) { // 신지혁
//		// 메뉴-카드(현금)-수량-금액
//	}
//
//	// 엑셀 export (메뉴별,결제별 매출)
//	public void exportToExcel(String salesType) {// 일찬님
//
//	}

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
			yourbill -= afterLunch.orderlist.get(i).menuItem.price;
			// point 적립
			int new_point = sonnom.customer.get("010-2222-3333")
					+ (int) (afterLunch.orderlist.get(i).menuItem.price * 0.05);
			sonnom.customer.put("010-2222-3333", new_point);

		}
		// 결제완료
		afterLunch.isPayed = true;
		System.out.println(yourbill);
		System.out.println("손님의 포인트 : " + sonnom.customer.toString());

	}
}
