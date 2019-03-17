package kr.or.bit.team1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import kr.or.bit.team1.util.TeamFormat;
import kr.or.bit.team1.util.TeamLogger;

public class Bucket implements Serializable {
		static long sequence = 1L;
		long bucketId;
		ArrayList<Orders> orderlist;
		Customers customer;
		Payments payment;
		boolean isPayed;

		public Bucket() {
			this.bucketId = sequence++;
			this.orderlist = new ArrayList<Orders>();
			this.customer = null;
			this.payment = null;
			this.isPayed = false;
		}

		/*
		 * @method name : listOrders
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 권순조
		 *
		 * @description : 주문내역을 메뉴별 수량의 합으로 보여준다
		 *
		 * @parameters :
		 *
		 * @return : void
		 */
		public void listOrders() {
			TeamLogger.info("listOrders");
			Set<String> menuSet = new HashSet<String>();

			for (int i = 0; i < orderlist.size(); i++) {
				if (menuSet.add(orderlist.get(i).menuItem.name)) {
					String name = orderlist.get(i).menuItem.name;
					int price = orderlist.get(i).menuItem.price;
					int quty = menuQty(orderlist.get(i).menuItem);
					int bill = price * quty;
					System.out.printf("메뉴: %s\t        단가: %d   수량: %d   금액: %d\n", name, price, quty, bill);
				}
			}
		}

		/*
		 * @method name : listOrdersId
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬
		 *
		 * @description : 주문내역을 주문 Id별로 보여준다 (단 결제된 주문은 제외)
		 *
		 * @parameters :
		 *
		 * @return : void
		 */
		public void listOrdersId() {
			for (int i = 0; i < orderlist.size(); i++) {
				if (orderlist.get(i).orderStatus.equals(OrderStatus.ORDER)) {
					long id = orderlist.get(i).orderId;
					String name = orderlist.get(i).menuItem.name;
					int price = orderlist.get(i).menuItem.price;
					System.out.printf("ID %d    메뉴: %s\t     금액: %d\n", id, name, price);
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
		 * @example 해당 메뉴를 2개 추가하면 qty=2, 2개 제외하면 qty=-2
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
		 * @date : 2019.03.14
		 *
		 * @author : 권순조
		 *
		 * @description : 현금으로 결제 금액 전액 처리한다.
		 *
		 * @parameters : int amount, Customers customer
		 *
		 * @return : void
		 */
		public void payCashAll(int amount, Customers customer) {
			TeamLogger.info("payCashAll");
			int point = 0;
			if (amount >= orderSum()) {
				int change = 0; // 거스름돈을 저장할 공간 선언

				Scanner sc = new Scanner(System.in);

				System.out.println("<Enter>결제    1.포인트 사용  2:포인트 적립  3:회원추가 ");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("1")) {
					System.out.println("고객 핸드폰번호를 입력하세요");

					String phoneNumber = sc.nextLine();
					if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // <enter>일때 에러가 나는 문제 미해결
						point = -1 * usePoints(phoneNumber, customer); // 포인트 사용시 -point로 해서 받을금액, 받은금액, 거스름돈 계산
					}
					if (customer.customer.containsKey(phoneNumber)) {
						// int point = customer.customer.get(s);
						change = amount - point - orderSum(); // 포인스 사용할 경우 -point로 바꾸기 위해 거스름돈 산식 변경

						Pos.amount = Pos.amount + amount + point - change;
						this.customer = customer.findCustomers(phoneNumber);
					} else {
						System.out.println("해당 고객이 없습니다.");
						return;
					}
				} else if (choice.equalsIgnoreCase("2")) {
					System.out.println("고객 핸드폰번호를 입력하세요");
					String phoneNumber = sc.nextLine();

					change = amount - orderSum();

					Pos.amount = Pos.amount + amount - change;

					if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // <enter>일때 에러가 나는 문제 미해결
						point = addPoints(customer, phoneNumber, orderSum());
					}

				} else if (choice.equalsIgnoreCase("3")) {
					addMember(customer);
					System.out.println("고객 핸드폰번호를 입력하세요");
					String phoneNumber = sc.nextLine();

					change = amount - orderSum();

					Pos.amount = Pos.amount + amount - change;

					if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // <enter>일때 에러가 나는 문제 미해결
						point = addPoints(customer, phoneNumber, orderSum());
					}

				} else {
					change = amount - orderSum();

					Pos.amount = Pos.amount + amount - change;
				}

				this.isPayed = true;
				for (int i = 0; i < this.orderlist.size(); i++) {
					this.orderlist.get(i).payment = new CashPayments();
					this.orderlist.get(i).orderStatus = OrderStatus.PAYED;
				}
				this.payment = new CashPayments();

				printReceipt(orderSum(), point, amount, change);// 영수증 출력
			} else {
				System.out.println("금액이 부족합니다.");
			}
		}

		/*
		 * @method name : payCardAll
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 이힘찬
		 *
		 * @description : 카드로 결제 금액 전액 처리한다.
		 *
		 * @parameters : Customers customer
		 *
		 * @return : void
		 */
		public void payCardAll(Customers customer) { // 이힘찬
			TeamLogger.info("payCardAll");
			int receivable = orderSum();
			int amount = receivable;
			int point = 0;
			int change = 0; // 거스름돈을 저장할 공간 선언

			Scanner sc = new Scanner(System.in);

			System.out.println("<Enter>결제    1.포인트 사용  2:포인트 적립  3:회원추가 ");
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("1")) {
				System.out.println("고객 핸드폰번호를 입력하세요");

				String phoneNumber = sc.nextLine();
				if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
					point = -1*usePoints(phoneNumber, customer);
				}
				if (customer.customer.containsKey(phoneNumber)) {
					// int point = customer.customer.get(s);
					amount += point;
					this.customer = customer.findCustomers(phoneNumber);
				} else {
					System.out.println("해당 고객이 없습니다.");
					return;
				}
			} else if (choice.equalsIgnoreCase("2")) {
				System.out.println("고객 핸드폰번호를 입력하세요");
				String phoneNumber = sc.nextLine();

				if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
					point = addPoints(customer, phoneNumber, amount);
				}

			} else if (choice.equalsIgnoreCase("3")) {
				addMember(customer);
				System.out.println("고객 핸드폰번호를 입력하세요");
				String phoneNumber = sc.nextLine();

				if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
					point = addPoints(customer, phoneNumber, amount);
				}

			} else { // 결제 (지우지 마세요)
			}

			this.isPayed = true;
			for (int i = 0; i < this.orderlist.size(); i++) {
				this.orderlist.get(i).payment = new CardPayments();
				this.orderlist.get(i).orderStatus = OrderStatus.PAYED;
			}
			this.payment = new CardPayments();

			printReceipt(receivable, point, amount, change);// 영수증 출력

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
		 * @parameters : int orderId, int amount, Customers customer
		 *
		 * @return : void
		 */
		public void payCash(int orderId, int amount, Customers customer) {
			TeamLogger.info("payCash");
			int i = this.orderlist.indexOf(this.getOrder(orderId));

			int point = 0;
			if (amount > this.orderlist.get(i).menuItem.price) {
				int change = 0; // 거스름돈을 저장할 공간 선언

				Scanner sc = new Scanner(System.in);

				System.out.println("<Enter>결제    1.포인트 사용  2:포인트 적립  3:회원추가 ");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("1")) {
					System.out.println("고객 핸드폰번호를 입력하세요");

					String phoneNumber = sc.nextLine();
					if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // <enter>일때 에러가 나는 문제 미해결
						point = -1 * usePoints(phoneNumber, customer); // 포인트 사용시 -point로 해서 받을금액, 받은금액, 거스름돈 계산
					}
					if (customer.customer.containsKey(phoneNumber)) {
						// int point = customer.customer.get(s);
						change = amount - point - this.orderlist.get(i).menuItem.price; // 포인스 사용할 경우 -point로 바꾸기 위해 거스름돈 산식
																						// 변경

						Pos.amount = Pos.amount + amount + point - change;
						this.customer = customer.findCustomers(phoneNumber);
					} else {
						System.out.println("해당 고객이 없습니다.");
						return;
					}
				} else if (choice.equalsIgnoreCase("2")) {
					System.out.println("고객 핸드폰번호를 입력하세요");
					String phoneNumber = sc.nextLine();

					change = amount - this.orderlist.get(i).menuItem.price;

					Pos.amount = Pos.amount + amount - change;

					if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // <enter>일때 에러가 나는 문제 미해결
						point = addPoints(customer, phoneNumber, this.orderlist.get(i).menuItem.price);
					}

				} else if (choice.equalsIgnoreCase("3")) {
					addMember(customer);
					System.out.println("고객 핸드폰번호를 입력하세요");
					String phoneNumber = sc.nextLine();

					change = amount - this.orderlist.get(i).menuItem.price;

					Pos.amount = Pos.amount + amount - change;

					if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // <enter>일때 에러가 나는 문제 미해결
						point = addPoints(customer, phoneNumber, this.orderlist.get(i).menuItem.price);
					}

				} else {
					change = amount - this.orderlist.get(i).menuItem.price;

					Pos.amount = Pos.amount + amount - change;
				}

				this.isPayed = true;
				this.orderlist.get(i).payment = new CashPayments();
				this.orderlist.get(i).orderStatus = OrderStatus.PAYED;
				this.payment = new CashPayments();

				printReceipt(this.orderlist.get(i).menuItem.price, point, amount, change);// 영수증 출력
			} else {
				System.out.println("금액이 부족합니다.");
			}
		}

		/*
		 * @method name : payCard
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 권순조
		 *
		 * @description : 카드로 결제 금액을 처리한다.
		 *
		 * @parameters : int orderId, Customers customer
		 *
		 * @return : void
		 */

		public void payCard(int orderId, Customers customer) {// 권예지
			int i = this.orderlist.indexOf(this.getOrder(orderId));
			
			int receivable=this.orderlist.get(i).menuItem.price;
			int amount = receivable;
			int point = 0;
			int change = 0; // 거스름돈을 저장할 공간 선언

			Scanner sc = new Scanner(System.in);

			System.out.println("<Enter>결제    1.포인트 사용  2:포인트 적립  3:회원추가 ");
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("1")) {
				System.out.println("고객 핸드폰번호를 입력하세요");

				String phoneNumber = sc.nextLine();
				
				if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
					point = -1*usePoints(phoneNumber, customer);
				}
				if (customer.customer.containsKey(phoneNumber)) {
					// int point = customer.customer.get(s);
					amount += point;
					this.customer = customer.findCustomers(phoneNumber);
				} else {
					System.out.println("해당 고객이 없습니다.");
					return;
				}
			} else if (choice.equalsIgnoreCase("2")) {
				System.out.println("고객 핸드폰번호를 입력하세요");
				String phoneNumber = sc.nextLine();

				if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
					point = addPoints(customer, phoneNumber, amount);
				}

			} else if (choice.equalsIgnoreCase("3")) {
				addMember(customer);
				System.out.println("고객 핸드폰번호를 입력하세요");
				String phoneNumber = sc.nextLine();

				if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
					point = addPoints(customer, phoneNumber, amount);
				}

			} else { // 결제 (지우지 마세요)
			}

			this.isPayed = true;
			this.orderlist.get(i).payment = new CardPayments();
			this.orderlist.get(i).orderStatus = OrderStatus.PAYED;
			this.payment = new CardPayments();

			printReceipt(receivable, point, amount, change);// 영수증 출력

		}

		/*
		 * @method name : payDutch
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬
		 *
		 * @description : Dutch pay
		 *
		 * @parameters : Customers customer
		 *
		 * @return : void
		 */
		public void payDutch(Customers customer) {
			Scanner sc = new Scanner(System.in);
			int amount = 0;
			int orderNum=this.orderlist.size();
			while (orderNum> 0) {
				System.out.println(this.orderlist.size());
				listOrdersId();

				System.out.println("메뉴 ID를 선택하세요 :");
				String choice = sc.nextLine();
				try {
					if (!isOrderId(Integer.parseInt(choice))) {
						System.out.println("id에 해당하는 메뉴가 없습니다.");
						return;
					}			
				} catch (Exception e) {
					System.out.println(e.getMessage());
					TeamLogger.info(e.getMessage());
				}
		
				// Orders order = getOrder(Integer.parseInt(choice));
				int orderId = Integer.parseInt(choice);

				while (true) {
					System.out.println("1:현금결제  2:카드결제");
					choice = sc.nextLine();
					if (choice.equalsIgnoreCase("1")) {
						System.out.println("받은 금액을 입력하세요 : ");
						amount = Integer.parseInt(sc.nextLine());
						payCash(orderId, amount, customer);
						orderNum--;
						break;
					} else if (choice.equalsIgnoreCase("2")) {
						payCard(orderId, customer);
						orderNum--;
						break;
					}
				}

			}

		}
		

		/*
		 * @method name : printReceipt
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 권예지
		 *
		 * @description : 영수증 출력 (Bucket단위당)
		 *
		 * @parameters : int totalAmount, int point, int cashAmount, int change
		 *
		 * @return : void
		 */
		public void printReceipt(int totalAmount, int point, int cashAmount, int change) {
			TeamLogger.info("printReceipt : Bucket");
			System.out.println("영수증 Id : " + this.bucketId + "\n");
			System.out.println("거래일시: " + TeamFormat.dateTimeFormat(new Date()));
			System.out.println("거래유형: " + this.payment.getPayType());
			System.out.println("할부기간: 일시불");
			System.out.println("=================================================");
			System.out.println("메뉴이름\t\t단가\t수량\t금액\t");
			System.out.println("=================================================");
			listOrders();
			System.out.println("=================================================");
			System.out.println("받을 금액 : " + totalAmount);
			System.out.println("적립포인트: " + point);
			System.out.println("받은 금액 : " + cashAmount);
			System.out.println("거스름돈  : " + change);
			System.out.println("=================================================");

		}

		public void printReceipt(Orders order, int totalAmount, int point, int cashAmount, int change) {
			TeamLogger.info("printReceipt : ");
			System.out.println("영수증 Id : " + this.bucketId + "\n");
			System.out.println("거래일시: " + TeamFormat.dateTimeFormat(new Date()));
			System.out.println("거래유형: " + order.payment.getPayType());
			System.out.println("할부기간: 일시불");
			System.out.println("=================================================");
			System.out.println("메뉴이름\t\t단가\t수량\t금액\t");
			System.out.println("=================================================");
			System.out.printf("메뉴: %s\t        단가: %d   수량: %d   금액: %d\n", order.menuItem.name, order.menuItem.price, 1,
					order.menuItem.price);
			System.out.println("=================================================");
			System.out.println("받을 금액 : " + totalAmount);
			System.out.println("적립포인트: " + point);
			System.out.println("받은 금액 : " + cashAmount);
			System.out.println("거스름돈  : " + change);
			System.out.println("=================================================");

		}

		/*
		 * @method name : addPoints
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 권예지
		 *
		 * @description : 포인트 추가
		 *
		 * @parameters : Customers customer, String phoneNumber, int amount
		 *
		 * @return : void
		 */
		public int addPoints(Customers customer, String phoneNumber, int amount) {
			int paypoint = customer.customer.get(phoneNumber);
			customer.customer.put(phoneNumber, (int) (paypoint + (orderSum() * 0.05)));
			return customer.customer.get(phoneNumber);
		}

		/*
		 * @method name : usePoints
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 권예지
		 *
		 * @description : 포인트를 사용한다. 사용후 포인트는 0
		 *
		 * @parameters : Customers customer
		 *
		 * @return : void
		 */
		public int usePoints(String phoneNumber, Customers customer) {
			int usePointsResult = 0;
			int result = 0;
			Scanner sc = new Scanner(System.in);

			if (!customer.customer.containsKey(phoneNumber)) {
				System.out.println("등록된 회원 핸드폰번호가 없습니다.");
				addMember(customer);
			} else {
				usePointsResult = customer.customer.get(phoneNumber);
				customer.customer.put(phoneNumber, 0);
			}
			return usePointsResult;
		}

		/*
		 * @method name : addMember
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 권예지
		 *
		 * @description : 회원을 추가한다.
		 *
		 * @parameters : Customers customer
		 *
		 * @return : void
		 */
		public void addMember(Customers customer) { // 권예지
			TeamLogger.info("addMember");
			Scanner sc = new Scanner(System.in);
			System.out.println("가입하시겠습니까? Y/N");
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("Y")) {
				System.out.println("핸드폰 번호를 입력하세요");
				String phoneNumber = sc.nextLine();
				customer.addCustomers(phoneNumber);
				System.out.println("고객 가입이 완료되었습니다.");
			} else {
				System.out.println("가입 취소");
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

		/*
		 * @method name : getOrder
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬
		 *
		 * @description : order id로 해당하는 order를 반환
		 *
		 * @parameters : int id
		 *
		 * @return : Orders
		 */
		public Orders getOrder(int id) {
			TeamLogger.info("getOrder");
			Orders order = null;
			for (int i = 0; i < this.orderlist.size(); i++) {
				if (orderlist.get(i).orderId.equals((long) id)) {
					order = orderlist.get(i);
				}
			}
			return order;
		}

		/*
		 * @method name : isOrderId
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬
		 *
		 * @description : order id로 해당하는 order가 있는지 여부
		 *
		 * @parameters : int id
		 *
		 * @return : boolean
		 */
		public boolean isOrderId(int id) {
			boolean isId = false;
			for (int i = 0; i < this.orderlist.size(); i++) {
				if (orderlist.get(i).orderId.equals((long) id)) {
					isId = true;
				}
			}
			return isId;

		}

		@Override
		public String toString() {
			return "Bucket [orderlist=" + orderlist + ", customer=" + customer + ", payment=" + payment + ", isPayed="
					+ isPayed + "]";
		}

	}