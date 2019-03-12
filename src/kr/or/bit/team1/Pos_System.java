package kr.or.bit.team1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import kr.or.bit.team1.util.TeamPatterns;


// 결제, 회원이 모호함


//Order 기능
//cancel
//discount
//refund
enum OrderStatus {ORDER, DISCOUNT, CANCEL, REFUND, PAYED};

//현금
//카드
enum PayType {CASH, CARD};

class Menu {
	String name;
	int price;
}

class Table {
	int tableNo;
	OrderList orderList;
	//HashMap<Integer, OrderList> tables= new HashMap<Integer, OrderList>();
	Date date;
	boolean isPayed;
	
	//테이블이동
	public void moveTable(int fromTable, int toTable) {// 강기훈 
		
	}
	//테이블주문합치기
	public void mergeTable(int fromTable, int toTable) {//권예지 
		
	}
}

// 중간에 담는 그릇이 필요
class OrderList {
	List<Orders> orderlist = new ArrayList<Orders>();
	Customers customer;
	
	//주문내역을 보여줌
	public void listOrders() {// 권순조 
		// 메뉴명-단가-수량-금액
	}
	
	//주문
	public void addOrder(Orders order) { // Menu menu 신지혁 
	
	}

	//선택취소
	public void deleteOrder(Orders order) { // 강기훈 
		
	}
	
	//전체취소
	public void deleteOrderAll() { // 신지혁 
		
	}
	
	// 에매한~ 수량변경
	public void changeQty(Menu menu, int qty) { // 일찬님 
		
	}
	
	//전부 현금결제 // 결제와 오더리스트의 연결이 애메함
	public void payCashAll(int amount) {// 권순조 
		//받을금액, 받은금액, 거스름돈
		//시재액과 연결이 미흡
		//테이블 초기화
		//영수증출력
	}
	
	//전부 카드결제
	public void payCardAll() { // 이힘찬 
		// 받은금액
		// 테이블초기화
		//영수증출력
	}
	
	public void payCash(int no, int amount) {//권순조 
		
	}
	
	public void payCard(int no, int amount) {//권예지 
		
	}
	
	public void payDivideAmount(int amount) {//일찬님 
		
	}
	
	
	
//	print receipt
	public void printReceipt() {// 권예지 
		
	}
	
	//회원등록
	public void addMembers(Customers customers) {// 신지혁 
	}
	
	//포인트 적립
	public void addPoints(Customers customers, String phoneNumber) {// 강기훈 
		
	}
	//포인트 사용
	public void usePoints(Customers customers, String phoneNumber) {// 힘찬이 
		
	}
	
	
	
	//포인트 사용

}

class Orders {
	static Long orderId;
	Date orderDate;
	Menu	menuItem;
	Payments payment;
	
	
}

interface Payments {
//	현금
//	카드
//	분할계산
	public void pay();
		
}

class CashPayments implements Payments {

	@Override
	public void pay() { //일찬님 
		System.out.println(PayType.CASH);
	}
	//포인트 적립
	public void addPoints(Customers customers, String phoneNumber) { //이힘찬 
		
	}
	//포인트 사용
	public void usePoints(Customers customers, String phoneNumber) {// 권순조 
		
	}
	
	
}

class CardPayments implements Payments {

	@Override
	public void pay() {// 신지혁 
		System.out.println(PayType.CARD);
	}
	
	//포인트 적립
	public void addPoints(Customers customers, String phoneNumber) {// 권예지 
		
	}
	//포인트 사용
	public void usePoints(Customers customers, String phoneNumber) {// 강기훈 
		
	}

}




class Customers {
	HashMap<String,Integer> customer = new HashMap<String,Integer>();// 키값: 전화번호,
	// 밸류값: 포인트 
	
	// 고객 추가
	public void addCustomers(String phoneNumber) {// 권순조 
		
	}
	// 고객 수정
	
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
		if(TeamPatterns.iscellPhoneMetPattern(phoneNumber)) {		// 핸드폰 정규표현식
			if(customer.containsKey(oldPhoneNumber)) {
				customer.put(phoneNumber, customer.get(oldPhoneNumber)); // 포인트를 새로운 핸드폰으로 옮김
				customer.remove(oldPhoneNumber);					     // 기존 폰넘버 삭제
			}
		} else {
			System.out.println("핸드폰번호를 확인하고 입력하세요");
		}
	}
	
	// 고객 조회
	public void findCustomers(String phoneNumber) { // 신지혁 
		
	}
	// 고객 탈퇴
	public void deleCustomers(String phoneNumber) { // 이힘찬 
		
	}
	
	// 고객현황
	public void listCustomers() {// 강기훈 
		
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
	
	// 주문(테이블)
	public void orderTable(Integer tableNo, Menu menu) { //일찬님 
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
	// 메뉴 추가
	public void addMenu(String name, Integer price) {// 이힘찬 
		
	}
	// 메뉴 수정
	public void modifyMenu(String oldname, String name, Integer price) {// 신지혁 
		
	}
	// 메뉴 삭제
	public void deleteMenu(String name) {// 권예지 
		
	}
	
	// 테이블관리
	List<Table> tables = new ArrayList<Table>();
	// 테이블 추가
	public void addTables() {// 권순조 
		
	}
	// 테이블 삭제
	public void deleteTables() {//강기훈 
		
	}
	
	// 고객관리
	Customers customers = new Customers(); 
	
	//고객가입
	
	//고객조회
	//고객탈퇴
	//고객현황
	
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
	public void printSalesMenu(String date) { //강기훈 
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
	public void save(String date) { //권예지 
		
	}
	// 데이터 로드 (시스템 시작시 데이터 로드)
	public void load(String date) {// 권예지 
		
	}
	// 일별 log // method마다 util에서 정의된 것을 사용
}

public class Pos_System {
	
	public static void main(String[] args) {
		Customers cu = new Customers();
		cu.customer.put("010-3350-8078", 10);
		cu.customer.put("010-3240-3377", 20);
		
		
		cu.modifyCustomers("010-3350-8078", "010-1111-2222");
		System.out.println(cu.customer.toString());

	}
}