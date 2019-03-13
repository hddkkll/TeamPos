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
enum OrderStatus {
	ORDER, DISCOUNT, CANCEL, REFUND, PAYED
};

//����
//ī��
enum PayType {
	CASH, CARD
};

class Menu {
	String name;
	int price;
}

class Table {
	int tableNo;
	OrderList orderList;
	// HashMap<Integer, OrderList> tables= new HashMap<Integer, OrderList>();
	Date date;
	boolean isPayed;

	// ���̺��̵�
	public void moveTable(int fromTable, int toTable) {// ������

	}

	// ���̺��ֹ���ġ��
	public void mergeTable(int fromTable, int toTable) {// �ǿ���

	}
}

// �߰��� ��� �׸��� �ʿ�
class OrderList {
	List<Orders> orderlist = new ArrayList<Orders>();
	Customers customer;

	// �ֹ������� ������
	public void listOrders() {// �Ǽ���
		// �޴���-�ܰ�-����-�ݾ�
		for (Orders or : orderlist) {
			System.out.println(or.menuItem.name + "/" + or.menuItem.price);
		}
	}

	// �ֹ�
	public void addOrder(Orders order) { // Menu menu ������

	}

	// �������
	public void deleteOrder(Orders order) { // ������

	}

	// ��ü���
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
	 * @description : �������� ���� �ݾ� ���� ó���Ѵ�.
	 *
	 * @parameters : int amount
	 *
	 * @return : void
	 */

	// ���� ���ݰ��� // ������ ��������Ʈ�� ������ �ָ���
	public void payCashAll(int amount) {// �Ǽ��� ���� ������ ������ ���պ��� ������ ���
		int exchange = 0;// �Ž������� ������ ���� ����
		exchange = amount - orderSum();// �����ݾ�, �����ݾ�, �Ž�����
		System.out.println(exchange);

		// ����װ� ������ ����
		// ���̺� �ʱ�ȭ
		// ���������
	}

	// ī�����
	public void payCardAll() { // ������
		// �����ݾ�
		// ���̺��ʱ�ȭ
		// ���������
	}

	/*
	 * @method name : payCash
	 *
	 * @date : 2019.03.12
	 *
	 * @author : �Ǽ���
	 *
	 * @description : �������� ���� �ݾ��� ó���Ѵ�.
	 *
	 * @parameters : int no, int amount > int amount
	 *
	 * @return : int
	 */

	public int payCash(int amount) {// �Ǽ���

		int result = 0;// ������ �ѹ� ����ϰ� ���� �ݾ��� ������ ����
		int sum = 0; // ������ ��ǰ�� ������ ���ϴ� ���� ����
		result = orderSum() - amount;// ������ ���տ��� ���� ������ ���� ���� ���� ����� ����
		return result; // ������ payDividieAmount���� ���
	}

	/*
	 * @method name : payCard
	 *
	 * @date : 2019.03.12
	 *
	 * @author : �Ǽ���
	 *
	 * @description : �������� ���� �ݾ��� ó���Ѵ�.
	 *
	 * @parameters : int no, int amount > int amount
	 *
	 * @return : int
	 */

	public int payCard(int amount) {// �ǿ���

		int result = 0;// ������ �ѹ� ����ϰ� ���� �ݾ��� ������ ����
		result = orderSum() - amount;// ������ ���տ��� ���� ������ ���� ���� ���� ����� ����
		return result; // ������ payDividieAmount���� ���
	}

	public void payDivideAmount(int no, int amount) {// ������
		if (no == 3) {
			do {
				System.out.println("���� ����� �����ϼ���");
				Scanner sc = new Scanner(System.in);
				int select = Integer.parseInt(sc.nextLine());
				if (select == 1) {// 1��
					System.out.println("���� ���縦 �����ϼ̽��ϴ�.");
					payCash(amount);

				} else if (select == 2) {
					System.out.println("ī�� ���縦 �����ϼ̽��ϴ�.");
					payCard(amount);
				}
			} while (payCard(amount) == 0 || payCash(amount) == 0);
		}
	}

	/*
	 * @method name : orderSum
	 *
	 * @date : 2019.03.12
	 *
	 * @author : �Ǽ���
	 *
	 * @description : ������ ��ǰ �ݾ��� �հ踦 ���Ѵ�.
	 *
	 * @parameters :
	 *
	 * @return : int
	 */
	public int orderSum() {
		int sum = 0; // ������ ��ǰ�� ������ ���ϴ� ���� ����
		for (int i = 0; i < orderlist.size(); i++) {// ������ ��ǰ�� ������ ���ϴ� ����
			Orders order = orderlist.get(i);
			sum += order.menuItem.price; // sum�� ����
		}
		return sum;// �հ踦 ��ȯ
	}

//	print receipt
	public void printReceipt() {// �ǿ���

	}

	// ȸ�����
	public void addMembers(Customers customers) {// ������
	}

	// ����Ʈ ����
	public void addPoints(Customers customers, String phoneNumber) {// ������

	}

	// ����Ʈ ���
	public void usePoints(Customers customers, String phoneNumber) {// ������

	}

	// ����Ʈ ���

}

class Orders {
	static Long orderId;
	Date orderDate;
	Menu menuItem;
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
	public void pay() { // ������
		System.out.println(PayType.CASH);
	}

	// ����Ʈ ����
	public void addPoints(Customers customers, String phoneNumber) { // ������

	}

	// ����Ʈ ���
	public void usePoints(Customers customers, String phoneNumber) {// �Ǽ���

	}

}

class CardPayments implements Payments {

	@Override
	public void pay() {// ������
		System.out.println(PayType.CARD);
	}

	// ����Ʈ ����
	public void addPoints(Customers customers, String phoneNumber) {// �ǿ���

	}

	// ����Ʈ ���
	public void usePoints(Customers customers, String phoneNumber) {// ������

	}

}

class Customers {
	HashMap<String, Integer> customer = new HashMap<String, Integer>();// Ű��: ��ȭ��ȣ,
	// �����: ����Ʈ

	// �� �߰�
	public void addCustomers(String phoneNumber) {// �Ǽ���
		Scanner sc = new Scanner(System.in);
		String PhonNum = sc.nextLine();
		customer.put(PhonNum, 0);

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

	// log ������丮
	String logPath = "C:\\temp\\log";

	// ����ݾ�
	Integer amount;
	List<Orders> orders = new ArrayList<Orders>();
	OrderList orderList;

	// �ǸŰ���, �������, ȸ������, �޴�����, ���̺����, �ý��� ����

	// �ֹ�(���̺�)
	public void orderTable(Integer tableNo, Menu menu) { // ������
		// ���̺� order add
	}

	// ���� (���̺�)
	public void payTableCash(Integer tableNo, Integer amount) {// ������
		// ���̺��� order�� �ϳ��� �����ͼ� ������
	}

	public void payTableCard(Integer tableNo) {// �Ǽ���

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
	public void deleteTables() {// ������

	}

	// ������
	Customers customers = new Customers();

	// ������

	// ����ȸ
	// ��Ż��
	// ����Ȳ

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
	public void printSalesMenu(String date) { // ������
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
	public void save(String date) { // �ǿ���

	}

	// ������ �ε� (�ý��� ���۽� ������ �ε�)
	public void load(String date) {// �ǿ���

	}
	// �Ϻ� log // method���� util���� ���ǵ� ���� ���
}

public class Pro1_Frame {
	public static void main(String[] args) {

	}
}