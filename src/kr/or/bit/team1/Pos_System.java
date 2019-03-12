package kr.or.bit.team1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


// ����, ȸ���� ��ȣ��


//Order ���
//cancel
//discount
//refund
enum OrderStatus {ORDER, DISCOUNT, CANCEL, REFUND, PAYED};

//����
//ī��
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
	
	//���̺��̵�
	public void moveTable(int fromTable, int toTable) {// ������ 
		
	}
	//���̺��ֹ���ġ��
	public void mergeTable(int fromTable, int toTable) {//�ǿ��� 
		
	}
}

// �߰��� ��� �׸��� �ʿ�
class OrderList {
	List<Orders> orderlist = new ArrayList<Orders>();
	Customers customer;
	
	//�ֹ������� ������
	public void listOrders() {// �Ǽ��� 
		// �޴���-�ܰ�-����-�ݾ�
		for(Orders or : orderlist) {
			
			System.out.println(""+or.menuItem.name+"/"+or.menuItem.price+"/"
			+or.menuItem.price);
		}
	}
	
	//�ֹ�
	public void addOrder(Orders order) { // Menu menu ������ 
	
	}

	//�������
	public void deleteOrder(Orders order) { // ������ 
		
	}
	
	//��ü���
	public void deleteOrderAll() { // ������ 
		
	}
	
	// ������~ ��������
	public void changeQty(Menu menu, int qty) { // ������ 
		
	}
	
	 /*
     * @method name : payCashAll
     *
     * @date : 2019.03.12
     *
     * @author : �Ǽ���
     *
     * @description : �������� ���� �ݾ��� ó���Ѵ�.
     *
     * @parameters : int amount
     *
     * @return : void
     */
	
	//���� ���ݰ��� // ������ ��������Ʈ�� ������ �ָ���
	public void payCashAll(int amount) {// �Ǽ��� 
		int exchange = 0;// �Ž������� ������ ���� ����
		int sum = 0; //������ ��ǰ�� ������ ���ϴ� ���� ����
		for(int i = 0; i<orderlist.size(); i++) {
			Orders order = orderlist.get(i);
			sum += order.menuItem.price;
		}
		exchange = sum - amount;//�����ݾ�, �����ݾ�, �Ž�����
		System.out.println(exchange);
		
		//����װ� ������ ����
		//���̺� �ʱ�ȭ
		//���������
	}
	
	//���� ī�����
	public void payCardAll() { // ������ 
		// �����ݾ�
		// ���̺��ʱ�ȭ
		//���������
	}
	
	public void payCash(int no, int amount) {//�Ǽ��� 
		
	}
	
	public void payCard(int no, int amount) {//�ǿ��� 
		
	}
	
	public void payDivideAmount(int amount) {//������ 
		
	}
	
	
	
//	print receipt
	public void printReceipt() {// �ǿ��� 
		
	}
	
	//ȸ�����
	public void addMembers(Customers customers) {// ������ 
	}
	
	//����Ʈ ����
	public void addPoints(Customers customers, String phoneNumber) {// ������ 
		
	}
	//����Ʈ ���
	public void usePoints(Customers customers, String phoneNumber) {// ������ 
		
	}
	
	
	
	//����Ʈ ���

}

class Orders {
	static Long orderId;
	Date orderDate;
	Menu	menuItem;
	Payments payment;
	
	
}

interface Payments {
//	����
//	ī��
//	���Ұ��
	public void pay();
		
}

class CashPayments implements Payments {

	@Override
	public void pay() { //������ 
		System.out.println(PayType.CASH);
	}
	//����Ʈ ����
	public void addPoints(Customers customers, String phoneNumber) { //������ 
		
	}
	//����Ʈ ���
	public void usePoints(Customers customers, String phoneNumber) {// �Ǽ��� 
		
	}
	
	
}

class CardPayments implements Payments {

	@Override
	public void pay() {// ������ 
		System.out.println(PayType.CARD);
	}
	
	//����Ʈ ����
	public void addPoints(Customers customers, String phoneNumber) {// �ǿ��� 
		
	}
	//����Ʈ ���
	public void usePoints(Customers customers, String phoneNumber) {// ������ 
		
	}

}




class Customers {
	HashMap<String,Integer> customer = new HashMap<String,Integer>();// Ű��: ��ȭ��ȣ,
	// �����: ����Ʈ 
	
	// �� �߰�
	public void addCustomers(String phoneNumber) {// �Ǽ��� 
		
	}
	// �� ����
	public void modifyCustomers(String oldPhoneNumber, String phoneNumber) {// ������ 
		
	}
	
	// �� ��ȸ
	public void findCustomers(String phoneNumber) { // ������ 
		
	}
	// �� Ż��
	public void deleCustomers(String phoneNumber) { // ������ 
		
	}
	
	// ����Ȳ
	public void listCustomers() {// ������ 
		
	}
}


class Pos {
	
	Scanner sc = new Scanner(System.in);
	
	//log ������丮
	String logPath = "C:\\temp\\log";
	
	// ����ݾ�
	Integer amount;
	List<Orders> orders = new ArrayList<Orders>();
	OrderList orderList;
	
	// �ǸŰ���, �������, ȸ������, �޴�����, ���̺����, �ý��� ����
	
	// �ֹ�(���̺�)
	public void orderTable(Integer tableNo, Menu menu) { //������ 
		// ���̺� order add
	}
	
	// ���� (���̺�)
	public void payTableCash(Integer tableNo, Integer amount) {// ������ 
		// ���̺��� order�� �ϳ��� �����ͼ� ������
	}

	public void payTableCard(Integer tableNo) {// �Ǽ��� 
		// ���̺��� order�� �ϳ��� �����ͼ� ������
	}

	// ���� (���̺�)
	public void payTableCardAll(Integer tableNo) { // ������ 
		// ���̺��� order�� �Ѱ��� ������������ 
	}
	
	public void payTableCashAll(Integer tableNo, Integer amount) {// ������ 
		// ���̺��� order�� �Ѱ��� ������������ 
	}
	
	
	
	List<Menu> menuItem = new ArrayList<Menu>();
	// �޴�����
	// �޴� �߰�
	public void addMenu(String name, Integer price) {// ������ 
		
	}
	// �޴� ����
	public void modifyMenu(String oldname, String name, Integer price) {// ������ 
		
	}
	// �޴� ����
	public void deleteMenu(String name) {// �ǿ��� 
		
	}
	
	// ���̺����
	List<Table> tables = new ArrayList<Table>();
	// ���̺� �߰�
	public void addTables() {// �Ǽ��� 
		
	}
	// ���̺� ����
	public void deleteTables() {//������ 
		
	}
	
	// ������
	Customers customers = new Customers(); 
	
	//������
	
	//����ȸ
	//��Ż��
	//����Ȳ
	
	// overloading
//	public void deleCustomers(String name) {
//		
//	}
	
	
		
	// ���ݰ���
	public void cashAdjustment() { // ������ 
		// ���ݽ������ �����ش�
		
	}
	// ����
	// �޴��� ���� (�Ϻ�)
	public void printSalesMenu(String date) { //������ 
		// �޴�-����-�ݾ�
	}

	// ������ ���� (�Ϻ�)
	public void printSalesPayment(String date) { // ������ 
		// �޴�-ī��(����)-����-�ݾ�
	}

	// ���� export (�޴���,������ ����)
	public void exportToExcel(String salesType) {// ������ 
		
	}

	// ������ ���� (�ý��� ����� ������ ����)
	public void save(String date) { //�ǿ��� 
		
	}
	// ������ �ε� (�ý��� ���۽� ������ �ε�)
	public void load(String date) {// �ǿ��� 
		
	}
	// �Ϻ� log // method���� util���� ���ǵ� ���� ���
}

public class Pos_System {
	public static void main(String[] args) {

	}
}