package kr.or.bit.team1;

import java.util.HashMap;
import java.util.Map;

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

		System.out.println(order1.toString());

		Bucket bucketList = new Bucket();
		bucketList.addOrder(order1);
		bucketList.addOrder(order2);
		bucketList.addOrder(order3);
		
		System.out.println(bucketList.orderlist.toString());
		//bucketList.changeQty(pos.getMenu("우동"), -1);
		System.out.println("짜장 주문수 : " + bucketList.menuQty(pos.getMenu("짜장")));
		System.out.println("짬뽕 주문수 : " + bucketList.menuQty(pos.getMenu("짬뽕")));

		System.out.println(bucketList.orderSum());
		
		pos.customers.addCustomers("010-1111-1111");
		pos.customers.addCustomers("010-2222-2222");
		
		System.out.println(pos.customers.findCustomers("010-1111-1111"));
	
		//bucketList.listOrders();
		
		//bucketList.payCashAll(20000, pos.customers);
		bucketList.payCardAll(pos.customers);
		System.out.println(bucketList.toString());
		
//		System.out.println("짬뽕의 object " + bucketList.getOrder(pos.getMenu("짬뽕")).toString());
//		//bucketList.deleteOrder(bucketList.getOrder(pos.getMenu("짬뽕")));
//		//bucketList.deleteOrder(bucketList.getOrder(pos.getMenu("짬뽕")));
//		//bucketList.deleteOrder(bucketList.getOrder(pos.getMenu("짬뽕")));
//		System.out.println("짜장 주문수 : " + bucketList.menuQty(pos.getMenu("짜장")));
//		System.out.println("짬뽕 주문수 : " + bucketList.menuQty(pos.getMenu("짬뽕")));
//		bucketList.deleteOrderAll();
//		System.out.println("짜장 주문수 : " + bucketList.menuQty(pos.getMenu("짜장")));
//		System.out.println("짬뽕 주문수 : " + bucketList.menuQty(pos.getMenu("짬뽕")));
//		bucketList.changeQty(pos.getMenu("우동"), -1);

	}
}
