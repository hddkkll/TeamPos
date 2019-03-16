package kr.or.bit.team1;

import java.util.Date;
import java.util.Iterator;

import kr.or.bit.team1.util.TeamFormat;

public class Test {
	public static void main(String[] args) {

		Pos pos = new Pos();

		pos.addMenu("짜장", 5000);
		pos.addMenu("짬뽕", 6000);
		pos.addMenu("우동", 5500);

		// Order 생성
		Orders order1 = new Orders(pos.getMenu("짜장"));
		Orders order2 = new Orders(pos.getMenu("짬뽕"));
		Orders order3 = new Orders(pos.getMenu("짬뽕"));

		// System.out.println(order1.toString());
		// System.out.println(order2.toString());
		// System.out.println(order3.toString());

		Bucket bucketList = new Bucket();
		bucketList.addOrder(order1);
		bucketList.addOrder(order2);
		bucketList.addOrder(order3);
		
		pos.orders.add(order1);
		pos.orders.add(order2);
		pos.orders.add(order3);
		// System.out.println(bucketList.orderlist.toString());
		// //bucketList.changeQty(pos.getMenu("우동"), -1);
		// System.out.println("짜장 주문수 : " + bucketList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + bucketList.menuQty(pos.getMenu("짬뽕")));

		// System.out.println(bucketList.orderSum());

		pos.customers.addCustomers("010-1111-1111");
		pos.customers.addCustomers("010-2222-2222");

		pos.tables.tableList.put(1, null);
		System.out.println(pos.tables.tableList.get(1) == null);
		// System.out.println(pos.customers.findCustomers("010-1111-1111"));

		// bucketList.listOrders();

		bucketList.payCashAll(20000, pos.customers);
		// bucketList.payCashAll(20000, pos.customers);
		// bucketList.payCardAll(pos.customers);

		// pos.orderList=bucketList;
		// System.out.println(pos.getQtyPerMenu("20190315", pos.getMenu("짬뽕"),
		// PayType.CARD));

		// bucketList.payCardAll(pos.customers);

		String date = TeamFormat.dateFormat(new Date());
//		pos.printSalesMenu(date);
		//pos.printSalesPayment(date);
		pos.exportToExcel(date);
//		String menuName = "";
//		int price = 0;
//		int qty = 0;
//		int sales = 0;
//		int totalSales = 0;
//
//		Iterator<Menu> itr = pos.menuList.iterator();
//
//		System.out.println("일별매출 ");
//		System.out.println("DATE: " + date);
//		System.out.println("=================================================");
//		System.out.println("메뉴이름\t\t단가\t수량\t금액\t");
//		System.out.println("=================================================");
//
//		while (itr.hasNext()) {
//			Menu menu = itr.next();
//			menuName = menu.name;
//			price = menu.price;
//			qty = pos.getQtyPerMenu(date, menu);
//			sales = price * qty;
//			totalSales += sales;
//			System.out.printf("%s\t        %d      %d      %d\n", menuName, price, qty, sales);
//		}
//		System.out.println("=================================================");
//		System.out.println("총매출 : " + totalSales);
//		System.out.println("=================================================");


		// System.out.println(bucketList.toString());
		// System.out.println(bucketList.orderlist.indexOf(bucketList.getOrder(2)));
		// bucketList.payDutch(pos.customers);
		// System.out.println("짬뽕의 object " +
		// bucketList.getOrder(pos.getMenu("짬뽕")).toString());
		// //bucketList.deleteOrder(bucketList.getOrder(pos.getMenu("짬뽕")));
		// //bucketList.deleteOrder(bucketList.getOrder(pos.getMenu("짬뽕")));
		// //bucketList.deleteOrder(bucketList.getOrder(pos.getMenu("짬뽕")));
		// System.out.println("짜장 주문수 : " + bucketList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + bucketList.menuQty(pos.getMenu("짬뽕")));
		// bucketList.deleteOrderAll();
		// System.out.println("짜장 주문수 : " + bucketList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + bucketList.menuQty(pos.getMenu("짬뽕")));
		// bucketList.changeQty(pos.getMenu("우동"), -1);

		// // ========================
		// // 이하 테스트용도
		//
		// Customers client = new Customers();
		// client.addCustomers("010-1111-1111");
		// client.addCustomers("010-2222-2222");
		// client.addCustomers("010-3333-3333");
		// client.deleCustomers("010-1111-2222");
		// client.findCustomers("010-2222-1111");
		//
		// System.out.println(client.toString());
		//
		// pos.addMenu("짜장", 5000);
		// pos.addMenu("짬뽕", 6000);
		// pos.addMenu("우동", 5500);
		//
		// Menu pickMenu = pos.getMenu("짜장");
		//
		// System.out.println(pickMenu.toString());
		//
		// // Order 생성
		// Orders order1 = new Orders(pos.getMenu("짜장"));
		// Orders order2 = new Orders(pos.getMenu("짬뽕"));
		// Orders order3 = new Orders(pos.getMenu("짬뽕"));
		//
		// System.out.println(order1.toString());
		//
		// // OrderList 생성
		// Bucket orderList = new Bucket();
		// orderList.addOrder(order1);
		// orderList.addOrder(order2);
		// orderList.addOrder(order3);
		//
		// System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
		//
		// System.out.println("짜장 5개 추가");
		// orderList.changeQty(pos.getMenu("짜장"), 5);
		//
		// System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
		//
		// System.out.println("짜장 6개 취소");
		// orderList.changeQty(pos.getMenu("짜장"), -6);
		//
		// System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
		//
		// System.out.println("우동 3개 추가");
		// orderList.changeQty(pos.getMenu("우동"), 3);
		//
		// System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
		// System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
		// System.out.println("우동 주문수 : " + orderList.menuQty(pos.getMenu("우동")));
		//
		// System.out.println(orderList.toString());
		//
		// // Table
		// Table tables = new Table();
		//
		// // add table
		// tables.addTable(1);
		// tables.addTable(2);
		// tables.addTable(3);
		// tables.addTable(4);
		// System.out.println(tables.tableList.toString());
		// // add OrderList to Table
		// tables.addOrderList(1, orderList);
		// System.out.println(tables.tableList.toString());
		//
		// // 결제
		// Customers sonnom = new Customers();
		// sonnom.addCustomers("010-2222-3333");
		// System.out.println("손님 : " + sonnom.customer.toString());
		//
		// int yourbill = 20000;
		// // cash
		// Bucket afterLunch = tables.tableList.get(1);
		// for (int i = 0; i < afterLunch.orderlist.size(); i++) {
		// yourbill -= afterLunch.orderlist.get(i).menuItem.price;
		// // point 적립
		// int new_point = sonnom.customer.get("010-2222-3333")
		// + (int) (afterLunch.orderlist.get(i).menuItem.price * 0.05);
		// sonnom.customer.put("010-2222-3333", new_point);
		//
		// }
		// // 결제완료
		// afterLunch.isPayed = true;
		// System.out.println(yourbill);
		// System.out.println("손님의 포인트 : " + sonnom.customer.toString());

	}
}
