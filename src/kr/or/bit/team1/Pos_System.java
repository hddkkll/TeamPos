package kr.or.bit.team1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import kr.or.bit.team1.util.TeamPatterns;

// 寃곗젣, �쉶�썝�씠 紐⑦샇�븿

//Order 湲곕뒫
//cancel
//discount
//refund
enum OrderStatus {
	ORDER, DISCOUNT, CANCEL, REFUND, PAYED
};

//�쁽湲�
//移대뱶
enum PayType {
	CASH, CARD
};

class Menu {
	String name;
	int price;
	Menu(String name, int price){
		this.name=name;
		this.price=price;
	}
}

class Table {
	int tableNo;
	OrderList orderList;
	// HashMap<Integer, OrderList> tables= new HashMap<Integer, OrderList>();
	Date date;
	boolean isPayed;

	// �뀒�씠釉붿씠�룞
	public void moveTable(int fromTable, int toTable) {// 媛뺢린�썕

	}

	// �뀒�씠釉붿＜臾명빀移섍린
	public void mergeTable(int fromTable, int toTable) {// 沅뚯삁吏�

	}
}

// 以묎컙�뿉 �떞�뒗 洹몃쫯�씠 �븘�슂
class OrderList {
	List<Orders> orderlist = new ArrayList<Orders>();
	Customers customer;

	// 二쇰Ц�궡�뿭�쓣 蹂댁뿬以�
	public void listOrders() {// 沅뚯닚議�
		// 硫붾돱紐�-�떒媛�-�닔�웾-湲덉븸
	}

	// 주문
	public void addOrder(Orders order) { // Menu menu 신지혁
		orderlist.add(order);
	}

	// �꽑�깮痍⑥냼
	public void deleteOrder(Orders order) { // 媛뺢린�썕

	}

	// 전체취소
	public void deleteOrderAll() { // 신지혁
		orderlist.removeAll(orderlist);
	}

	// �뿉留ㅽ븳~ �닔�웾蹂�寃�
	public void changeQty(Menu menu, int qty) { // �씪李щ떂

	}

	// �쟾遺� �쁽湲덇껐�젣 // 寃곗젣�� �삤�뜑由ъ뒪�듃�쓽 �뿰寃곗씠 �븷硫뷀븿
	public void payCashAll(int amount) {// 沅뚯닚議�
		// 諛쏆쓣湲덉븸, 諛쏆�湲덉븸, 嫄곗뒪由꾨룉
		// �떆�옱�븸怨� �뿰寃곗씠 誘명씉
		// �뀒�씠釉� 珥덇린�솕
		// �쁺�닔利앹텧�젰
	}

	// �쟾遺� 移대뱶寃곗젣
	public void payCardAll() { // �씠�옒李�
		// 諛쏆�湲덉븸
		// �뀒�씠釉붿큹湲고솕
		// �쁺�닔利앹텧�젰
	}

	public void payCash(int no, int amount) {// 沅뚯닚議�

	}

	public void payCard(int no, int amount) {// 沅뚯삁吏�

	}

	public void payDivideAmount(int amount) {// �씪李щ떂

	}

//	print receipt
	public void printReceipt() {// 沅뚯삁吏�

	}

	// 회원등록
	public void addMembers(String phoneNumber) {// 신지혁
		customer.customer.put(phoneNumber, 0);
	}

	// �룷�씤�듃 �쟻由�
	public void addPoints(Customers customers, String phoneNumber) {// 媛뺢린�썕

	}

	// �룷�씤�듃 �궗�슜
	public void usePoints(Customers customers, String phoneNumber) {// �옒李ъ씠

	}

	// �룷�씤�듃 �궗�슜

}

class Orders {
	static Long orderId;
	Date orderDate;
	Menu menuItem;
	Payments payment;

}

interface Payments {
//	�쁽湲�
//	移대뱶
//	遺꾪븷怨꾩궛
	public void pay();

}

class CashPayments implements Payments {

	@Override
	public void pay() { // �씪李щ떂
		System.out.println(PayType.CASH);
	}

	// �룷�씤�듃 �쟻由�
	public void addPoints(Customers customers, String phoneNumber) { // �씠�옒李�

	}

	// �룷�씤�듃 �궗�슜
	public void usePoints(Customers customers, String phoneNumber) {// 沅뚯닚議�

	}

}

class CardPayments implements Payments {

	@Override
	public void pay() {// �떊吏��쁺
		System.out.println(PayType.CARD);
	}

	// �룷�씤�듃 �쟻由�
	public void addPoints(Customers customers, String phoneNumber) {// 沅뚯삁吏�

	}

	// �룷�씤�듃 �궗�슜
	public void usePoints(Customers customers, String phoneNumber) {// 媛뺢린�썕

	}

}

class Customers {
	HashMap<String, Integer> customer = new HashMap<String, Integer>();// �궎媛�: �쟾�솕踰덊샇,
	// 諛몃쪟媛�: �룷�씤�듃

	// 怨좉컼 異붽�
	public void addCustomers(String phoneNumber) {// 沅뚯닚議�

	}
	// 怨좉컼 �닔�젙

	/*
	 * @method name : modifyCustomers
	 * 
	 * @date : 2019.03.12
	 * 
	 * @author : �젙�씪李�
	 * 
	 * @description : 怨좉컼�젙蹂대�� �닔�젙�븳�떎.
	 * 
	 * @parameters : String oldPhoneNumber, String phoneNumber
	 * 
	 * @return : void
	 */
	public void modifyCustomers(String oldPhoneNumber, String phoneNumber) {
		if (TeamPatterns.iscellPhoneMetPattern(phoneNumber)) { // �빖�뱶�룿 �젙洹쒗몴�쁽�떇
			if (customer.containsKey(oldPhoneNumber)) {
				customer.put(phoneNumber, customer.get(oldPhoneNumber)); // �룷�씤�듃瑜� �깉濡쒖슫 �빖�뱶�룿�쑝濡� �삷源�
				customer.remove(oldPhoneNumber); // 湲곗〈 �룿�꽆踰� �궘�젣
			}
		} else {
			System.out.println("�빖�뱶�룿踰덊샇瑜� �솗�씤�븯怨� �엯�젰�븯�꽭�슂");
		}
	}

	// 고객 조회
	public void findCustomers(String phoneNumber) { // 신지혁 
		if(customer.get(phoneNumber)!=null)
			System.out.println(phoneNumber + "의 포인트는 : " + customer.get(phoneNumber) + "원 입니다");
		else
			System.out.println("고객이아닙니다");
	}

	// 怨좉컼 �깉�눜
	public void deleCustomers(String phoneNumber) { // �씠�옒李�

	}

	// 怨좉컼�쁽�솴
	public void listCustomers() {// 媛뺢린�썕

	}
}

class Pos {

	Scanner sc = new Scanner(System.in);

	// log ���옣�뵒�젆�넗由�
	String logPath = "C:\\temp\\log";

	// �떆�옱湲덉븸
	Integer amount;
	List<Orders> orders = new ArrayList<Orders>();
	OrderList orderList;

	// �뙋留ㅺ�由�, 留ㅼ텧愿�由�, �쉶�썝愿�由�, 硫붾돱愿�由�, �뀒�씠釉붽�由�, �떆�뒪�뀥 醫낅즺

	// 二쇰Ц(�뀒�씠釉�)
	public void orderTable(Integer tableNo, Menu menu) { // �씪李щ떂
		// �뀒�씠釉붿뿉 order add
	}

	// 寃곗젣 (�뀒�씠釉�)
	public void payTableCash(Integer tableNo, Integer amount) {// �씠�옒李�
		// �뀒�씠釉붿뿉�꽌 order瑜� �븯�굹�뵫 媛��졇���꽌 寃곗젣�븿
	}

	public void payTableCard(Integer tableNo) {// 沅뚯닚議�
		// �뀒�씠釉붿뿉�꽌 order瑜� �븯�굹�뵫 媛��졇���꽌 寃곗젣�븿
	}

	// 寃곗젣 (�뀒�씠釉�)
	public void payTableCardAll(Integer tableNo) { // �씪李щ떂
		// �뀒�씠釉붿쓽 order瑜� �븳媛�吏� 寃곗젣�삎�떇�쑝濡�
	}

	public void payTableCashAll(Integer tableNo, Integer amount) {// �씠�옒李�
		// �뀒�씠釉붿쓽 order瑜� �븳媛�吏� 寃곗젣�삎�떇�쑝濡�
	}

	List<Menu> menuItem = new ArrayList<Menu>();

	// 硫붾돱愿�由�
	// 硫붾돱 異붽�
	public void addMenu(String name, Integer price) {// �씠�옒李�

	}

	// 메뉴 수정
	public void modifyMenu(String oldname, String name, Integer price) {// 신지혁
		for(int i = 0; i<menuItem.size();i++) {
			if(menuItem.get(i).name.equals(oldname)) {
				System.out.println("dddddd");
				menuItem.remove(i);
				menuItem.add(new Menu(name, price));
				break;
			}
		}	
	}

	// 硫붾돱 �궘�젣
	public void deleteMenu(String name) {// 沅뚯삁吏�

	}

	// �뀒�씠釉붽�由�
	List<Table> tables = new ArrayList<Table>();

	// �뀒�씠釉� 異붽�
	public void addTables() {// 沅뚯닚議�

	}

	// �뀒�씠釉� �궘�젣
	public void deleteTables() {// 媛뺢린�썕

	}

	// 怨좉컼愿�由�
	Customers customers = new Customers();

	// 怨좉컼媛��엯

	// 怨좉컼議고쉶
	// 怨좉컼�깉�눜
	// 怨좉컼�쁽�솴

	// overloading
//	public void deleCustomers(String name) {
//		
//	}

	// �쁽湲덇�由�
	public void cashAdjustment() { // �씠�옒李�
		// �쁽湲덉떆�옱�븸�쓣 蹂댁뿬以��떎

	}

	// 留ㅼ텧
	// 硫붾돱蹂� 留ㅼ텧 (�씪蹂�)
	public void printSalesMenu(String date) { // 媛뺢린�썕
		// 硫붾돱-�닔�웾-湲덉븸
	}

	// 寃곗젣蹂� 留ㅼ텧 (�씪蹂�)
	public void printSalesPayment(String date) { // �떊吏��쁺
		// 硫붾돱-移대뱶(�쁽湲�)-�닔�웾-湲덉븸
	}

	// �뿊�� export (硫붾돱蹂�,寃곗젣蹂� 留ㅼ텧)
	public void exportToExcel(String salesType) {// �씪李щ떂

	}

	// �뜲�씠�꽣 ���옣 (�떆�뒪�뀥 醫낅즺�떆 �뜲�씠�꽣 ���옣)
	public void save(String date) { // 沅뚯삁吏�

	}

	// �뜲�씠�꽣 濡쒕뱶 (�떆�뒪�뀥 �떆�옉�떆 �뜲�씠�꽣 濡쒕뱶)
	public void load(String date) {// 沅뚯삁吏�

	}
	// �씪蹂� log // method留덈떎 util�뿉�꽌 �젙�쓽�맂 寃껋쓣 �궗�슜
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
