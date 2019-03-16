package kr.or.bit.team1;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import kr.or.bit.team1.util.TeamFiles;
import kr.or.bit.team1.util.TeamFormat;
import kr.or.bit.team1.util.TeamLogger;

enum OrderStatus {
	ORDER, DISCOUNT, CANCEL, REFUND, PAYED
};

enum PayType {
	CASH, CARD
};

public class Pos implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		static Scanner sc = new Scanner(System.in);

		// log 저장디렉토리
		String logPath = "C:\\temp\\log";

		// 시재금액
		static int amount = 200000;
		List<Orders> orders = new ArrayList<Orders>();
		Bucket orderList;
		Table tables = new Table();
		List<Menu> menuList = new ArrayList<Menu>();
		Customers customers = new Customers();
		static int tableCount = 9; // tableCount의 default 값은 9!

		// log경로 설정
		Pos() {
			TeamLogger.getLogger(logPath);
			TeamLogger.info("POS System v0.1");
			TeamLogger.info("@copyleft TeamSoft 2019");

			// load(date);
		}

		public void totalsales() {
			int num = 0;
			String date = null;
			System.out.println("\n<<매출관리 메뉴>>");
			System.out.println("1.메뉴 별 매출");
			System.out.println("2.결제 별 매출");
			System.out.println("3.엑셀로 저장");
			System.out.print("선택 : ");
			num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				System.out.print("메뉴 입력 : ");
				date = sc.nextLine();
				printSalesMenu(date);
				break;
			case 2:
				System.out.print("결제 방식 입력(카드or현금) : ");
				date = sc.nextLine();
				printSalesPayment(date);
				break;
			case 3:
				System.out.println("엑셀로 저장할 매출을 선택하세요");
				exportToExcel(date);
				break;
			default:
			}
		}

		public void viewTable(int tableCount) {

			for (int i = 1; i <= tableCount; i++) {

//		   if(tables.tablelist.get(i).orderlist.isEmpty()) {	
//			   tables.tablelist.remove(i);

				if (tables.tableList.get(i) != null) {
					if (tables.tableList.get(i).orderlist.isEmpty()) {
						System.out.printf("[%d □]", i);

					} else {
						System.out.printf("[%d ■]", i);
					}
				} else {

					System.out.printf("[%d □]", i);

				}

				if (i % 3 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println("■: 손님이 있는 테이블 , □: 빈 테이블");

		}

		void menuManage() {

			while (true) {
				System.out.println(menuList.toString());

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
				System.out.println();
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
					//posStart();
					return;
				default:
					System.out.println("다시 입력하세요");

				}

			}

		}

		void memberManage() {
			while (true) {
				int num = 0;
				String phoneNumber = "";
				String oldphoneNumber = "";
				System.out.println("회원을 추가, 수정, 조회, 탈퇴, 현황을 볼수 있습니다");
				System.out.println("1.회원 추가");
				System.out.println("2.회원 수정");
				System.out.println("3.회원 조회");
				System.out.println("4.회원 탈퇴");
				System.out.println("5.회원 현황");
				System.out.println("0.종료");
				System.out.println("원하는 번호를 입력하세요");
				num = Integer.parseInt(sc.nextLine());

				switch (num) {
				case 0:
					return;
				case 1:
					System.out.println("추가할 회원의 번호를 입력해주세요");
					phoneNumber = sc.nextLine();
					addMembers(phoneNumber);
					break;

				case 2:
					System.out.println("회원정보를 수정합니다");
					System.out.println("oldPhoneNumber를 입력해주세요");
					oldphoneNumber = sc.nextLine();
					System.out.println("새로운 PhoneNumber를 입력해주세요");
					phoneNumber = sc.nextLine();

					modifyMembers(oldphoneNumber, phoneNumber);
					break;

				case 3:
					System.out.println("조회할 전화번호를 입력해주세요");
					phoneNumber = sc.nextLine();
					findMembers(phoneNumber);
					break;

				case 4:
					System.out.println("탈퇴할 전화번호를 입력해주세요");
					phoneNumber = sc.nextLine();
					deleteMembers(phoneNumber);
					break;

				case 5:
					System.out.println("회원현황 입니다");
					listMembers();
					break;

				default:
					break;
				}
			}

		}

		// 고객수정
		public void modifyMembers(String oldPhoneNumber, String phoneNumber) {// 신지혁
			customers.modifyCustomers(oldPhoneNumber, phoneNumber);

		}

		// 고객조회
		public void findMembers(String phoneNumber) {// 신지혁
			customers.findCustomers(phoneNumber);

		}

		// 고객탈퇴
		public void deleteMembers(String phoneNumber) {// 신지혁
			customers.deleCustomers(phoneNumber);

		}

		// 고객현황
		public void listMembers() {// 신지혁
			customers.listCustomers();

		}

		void selectTable() {
			viewTable(tableCount);
			int tableNum = 0;
			System.out.println();
			System.out.println("테이블을 선택하세요!  0번: 뒤로가기");
			tableNum = Integer.parseInt(sc.nextLine());
			if (tableNum == 0) {
				return;
			}
			if (tables.tableList.get(tableNum) == null) {
				createTable(tableNum);
			}
			showMenu(tableNum);

			// System.out.println(orderlist.listOrders()); listOrders(): 반환값을 스트링으로 리턴해줄 것 !

		}

		public int displayMenu() {
			int menuNum = 0;
			System.out.println("=====POS SYSTEM=====");
			System.out.println();
			System.out.println("1.판매관리");
			System.out.println("2.매출관리");
			System.out.println("3.회원관리");
			System.out.println("4.메뉴관리");
			System.out.println("5.테이블관리");
			System.out.println("6.시스템종료");

			System.out.print("번호를 입력하세요:");
			menuNum = Integer.parseInt(sc.nextLine());
			return menuNum;
			
		}

		public void posStart() {
			menuLoop: while (true) {
				switch (displayMenu()) {

				case 1:
					selectTable();
					break;

				case 2:
					totalsales();
					break;

				case 3:
					memberManage();
					break;
				case 4:
					menuManage();

					break;

				case 5:
					tableManage();
					break;

				case 6:
					System.out.println("시스템 종료");
					break menuLoop;

				}

			}

		}

		void createTable(int tableNum) {

			orderList = new Bucket();
			tables.tableList.put(tableNum, orderList);
		}

		void showMenu(int tableNum) {
			int menuNum = 0;

			while (true) {
				System.out.println("메뉴를 선택하세요");
				System.out.println("1.주문하기");
				System.out.println("2.주문삭제");
				System.out.println("3.주문전체삭제");
				System.out.println("4.주문내역보기");
				System.out.println("5.수량변경");
				System.out.println("6.테이블 이동");
				System.out.println("7.테이블 합석");
				System.out.println("8.결제하기");
				System.out.println("0.뒤로가기");

				menuNum = Integer.parseInt(sc.nextLine());
				while (menuNum < 0 || menuNum > 8) {
					System.out.println("잘못입력하셨습니다. 다시 입력하세요");
					menuNum = Integer.parseInt(sc.nextLine());
				}

				switch (menuNum) {
				case 0:
					//selectTable();
					return;
				case 1:

					System.out.println("======== MENU ========");
					for (int i = 0; i < menuList.size(); i++) {
						System.out.printf("%d. %s : %d 원\n", i + 1, menuList.get(i).name, menuList.get(i).price);

					}

					while (true) {
						System.out.println("메뉴를 선택하세요. 뒤로가기는 0번");
						if(menuList.size()>0) {										// 예외처리 추가
							int choiceNum = Integer.parseInt(sc.nextLine());
							// System.out.println( menuList.get(choiceNum -1));
							if (choiceNum == 0) {
								break;
							}

							tables.tableList.get(tableNum).orderlist.add(new Orders(getMenu(menuList.get(choiceNum - 1).name)));
							System.out.println(menuList.get(choiceNum - 1).name + "이(가) 주문되었습니다. ");
						} else {
							System.out.println("선택할 메뉴가 없습니다. 초기 메뉴로 돌아갑니다.");
							return;
						}															// 예외처리

					}

					break;

				case 2:
					System.out.println("취소할 주문을 선택하세요");

					tables.tableList.get(tableNum).listOrders();

					String menuName2 = sc.nextLine();
					if (menuName2.equals("0")) {
						return;
					}

					tables.tableList.get(tableNum).deleteOrder(tables.tableList.get(tableNum).getOrder(getMenu(menuName2)));
					System.out.println(menuName2 + "이(가) 취소되었습니다.");

					break;
				case 3:

					tables.tableList.get(tableNum).deleteOrderAll();
					System.out.println("모든 주문이 삭제되었습니다.");

					break;

				case 4:
					System.out.println("========주문 내역========");
					tables.tableList.get(tableNum).listOrders();

					break;

				case 5:
					System.out.println("수량을 변경할 메뉴를 선택하세요");
					String menuName3 = sc.nextLine();
					System.out.println("추가하고 싶은 수량을 입력하세요");
					int qty = Integer.parseInt(sc.nextLine());

					for (int i = 0; i < menuList.size(); i++) {
						if (menuName3.equals(menuList.get(i).name)) {
							tables.tableList.get(tableNum).changeQty(menuList.get(i), qty);

							System.out.println(menuList.get(i).name + "의 수량이 변경되었습니다.");
						}
					}
					break;

				case 6:
					System.out.println("이동할곳의 테이블번호를 입력하세요");
					int toTable = Integer.parseInt(sc.nextLine());
					tables.moveTable(tableNum, toTable);
					break;
				case 7:
					System.out.println("합석할 테이블을 선택하세요");
					toTable = Integer.parseInt(sc.nextLine());
					tables.mergeTable(tableNum, toTable);

					break;

				case 8:
					int payMenu = 0;

					System.out.println("카드결제와 현금결제중에 선택하세요!");
					System.out.println("1.카드결제");
					System.out.println("2.현금결제");
					System.out.println("3.분할계산");
					payMenu = Integer.parseInt(sc.nextLine());

					switch (payMenu) {

					case 1:
						tables.tableList.get(tableNum).payCardAll(customers);

						break;

					case 2:
						int amount = 0;

						System.out.println("돈을 주세요:");
						amount = Integer.parseInt(sc.nextLine());

						tables.tableList.get(tableNum).payCashAll(amount, customers);

						break;
					case 3:
						System.out.println();
						tables.tableList.get(tableNum).payDutch(customers);

						break;
					}

					break;

				}

			}
		}

		void tableManage() {
			while (true) {
				int num = 0;
				System.out.println("테이블 관리 입니다");
				System.out.println("현재 테이블의 개수는 " + tableCount + "개 입니다.");
				System.out.println("1. 테이블추가");
				System.out.println("2. 테이블삭제");
				System.out.println("0. 종료");
				System.out.println("번호를 입력하세요");
				num = Integer.parseInt(sc.nextLine());

				switch (num) {
				case 0:
					return;
				case 1:
					System.out.println("테이블 1개를 추가합니다");

					tableCount++;

					break;
				case 2:
					System.out.println("테이블 1개를 삭제합니다");
					tableCount--;
					break;

				default:
					break;
				}

			}
		}

		// 판매관리, 매출관리, 회원관리, 메뉴관리, 테이블관리, 시스템 종료
		// 테이블 주문 합치기
		public void mergeTable(int fromTable, int toTable) {// 권예지
			this.tables.mergeTable(fromTable, toTable);
		}

		// 주문(테이블)
		public void orderTable(Integer tableNo, Menu menu) { // 일찬님
		}

		public void payTableCash(Integer tableNo) {// 이힘찬
			// 테이블에서 order를 하나씩 가져와서 결제함
			for (int i = 0; i < tables.tableList.get(tableNo).orderlist.size(); i++) {
				String menu = tables.tableList.get(tableNo).orderlist.get(i).menuItem.name; // 메뉴이름
				int price = tables.tableList.get(tableNo).orderlist.get(i).menuItem.price; // 가격
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
			int price = tables.tableList.get(tableNo).orderSum(); // 가격
			int change = amount - price; // 잔돈
			System.out.println("받은돈 : amount");
			System.out.println("잔   돈 : change");
		}

		// 메뉴관리
		// 메뉴 추가
		public void addMenu(String name, Integer price) {// 이힘찬
			menuList.add(new Menu(name, price));
		}

		// 메뉴 수정
		public void modifyMenu(String oldname, String name, Integer price) {// 신지혁
			for (int i = 0; i < menuList.size(); i++) {
				if (menuList.get(i).name.equals(oldname)) {
					menuList.remove(i);
					menuList.add(new Menu(name, price));
					break;
				}
			}
		}

		// 메뉴 삭제
		public void deleteMenu(String name) {// 권예지
			for (int i = 0; i < menuList.size(); i++)
				if (menuList.get(i).name.equals(name))
					menuList.remove(i);
		}

		public Menu getMenu(String name) {
			Menu menu = null;
			for (Menu m : menuList) {
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
//		public void deleCustomers(String name) {
//			
//		}

		// 현금관리
		public void cashAdjustment() { // 이힘찬
			// 현금시재액을 보여준다
			System.out.println("현금시재액 : " + amount);
		}

		// 매출
		/*
		 * @method name : printSalesPayment
		 *
		 * @date : 2019.03.16
		 *
		 * @author : 강기훈 
		 *
		 * @description : 일별 메뉴별 매출액을 구한다  
		 *
		 * @parameters : String date
		 *
		 * @return : void 
		 */
		public void printSalesMenu(String date) { // 강기훈			
			// 메뉴-수량-금액
			TeamLogger.info("printSalesMenu(String date)");
			String menuName = "";
			int price = 0;
			int qty = 0;
			int sales = 0;
			int totalSales = 0;

			Iterator<Menu> itr = menuList.iterator();

			System.out.println("일별매출 ");
			System.out.println("DATE: " + date);
			System.out.println("=================================================");
			System.out.println("메뉴이름\t\t단가\t수량\t금액\t");
			System.out.println("=================================================");

			while (itr.hasNext()) {
				Menu menu = itr.next();
				menuName = menu.name;
				price = menu.price;
				qty = getQtyPerMenu(date, menu);
				sales = price * qty;
				totalSales += sales;
				System.out.printf("%s\t        %d      %d      %s\n", menuName, price, qty, TeamFormat.amountFormat(sales));
			}
			System.out.println("=================================================");
			System.out.println("총매출 : " + TeamFormat.amountFormat(totalSales));
			System.out.println("=================================================");
		}

		/*
		 * @method name : printSalesPayment
		 *
		 * @date : 2019.03.16
		 *
		 * @author : 신지혁 
		 *
		 * @description : 일별 메뉴별, 현금, 카드별 매출액을 구한다  
		 *
		 * @parameters : String date
		 *
		 * @return : void 
		 */
		public void printSalesPayment(String date) { 
			TeamLogger.info("printSalesPayment(String date)");
			// 메뉴-카드(현금)-수량-금액
			printSalesPayment(date, PayType.CASH);
			printSalesPayment(date, PayType.CARD);
			
		}
		
		/*
		 * @method name : printSalesPayment
		 *
		 * @date : 2019.03.16
		 *
		 * @author : 정일찬 
		 *
		 * @description : 일별 메뉴별, 결제별 매출액을 구한다  
		 *
		 * @parameters : String date, PayType payType
		 *
		 * @return : void 
		 */
		public void printSalesPayment(String date, PayType payType) {
			TeamLogger.info("printSalesPayment(String date, PayType payType)");
			String menuName = "";
			int price = 0;
			int qty = 0;
			int sales = 0;
			int totalSales = 0;

			Iterator<Menu> itr = menuList.iterator();

			System.out.println("일별매출 : " + payType);
			System.out.println("DATE: " + date);
			System.out.println("=================================================");
			System.out.println("메뉴이름\t\t단가\t수량\t금액\t");
			System.out.println("=================================================");

			while (itr.hasNext()) {
				Menu menu = itr.next();
				menuName = menu.name;
				price = menu.price;
				qty = getQtyPerMenu(date, menu, payType);
				sales = price * qty;
				totalSales += sales;
				System.out.printf("%s\t        %d      %d      %s\n", menuName, price, qty, TeamFormat.amountFormat(sales));
			}
			System.out.println("=================================================");
			System.out.println("총매출 : " + TeamFormat.amountFormat(totalSales));
			System.out.println("=================================================");
			
		}
		
		/*
		 * @method name : getQtyPerMenu
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬 
		 *
		 * @description : 일별, 메뉴멸 판매수량을 구한다 
		 *
		 * @parameters : String date, Menu menu, PayType payType
		 *
		 * @return : int
		 */
		public int getQtyPerMenu(String date, Menu menu, PayType payType) {
			TeamLogger.info("getQtyPerMenu(String date, Menu menu, PayType payType)");
			int qty = 0;
			for (int i = 0; i < this.orders.size(); i++) {
				if (TeamFormat.dateFormat(this.orders.get(i).orderDate)
						.equalsIgnoreCase(date)) {
					if (orders.get(i).menuItem.name.trim().equalsIgnoreCase(menu.name.trim())) {
						if (orders.get(i).payment.getPayType().equals(payType)) {
							qty++;
						}
					}
				}
			}
			return qty;
		}
		
		/*
		 * @method name : getQtyPerMenu
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬 
		 *
		 * @description : 일별, 메뉴별 판매수량을 구한다 
		 *
		 * @parameters : String date, Menu menu
		 *
		 * @return : int
		 */
		public int getQtyPerMenu(String date, Menu menu) {
			TeamLogger.info("getQtyPerMenu(String date, Menu menu)");
			int qty = 0;
			System.out.println(this.orders.size());
			for (int i = 0; i < this.orders.size(); i++) {
				if (TeamFormat.dateFormat(this.orders.get(i).orderDate)
						.equalsIgnoreCase(date)) {
					if (this.orders.get(i).menuItem.name.trim().equalsIgnoreCase(menu.name.trim())) {
							qty++;
					}
				}
			}
			return qty;
		}

		/*
		 * @method name : exportToExcel
		 *
		 * @date : 2019.03.15
		 *
		 * @author : 정일찬 
		 *
		 * @description : 일별 판매수량을 엑셀파일로 저장한다. 
		 *
		 * @parameters : String date
		 *
		 * @return : void
		 */
		public void exportToExcel(String date) {
			String menuName = "";
			int price = 0;
			int qty = 0;
			int sales = 0;

			List<Sales> list=new ArrayList<Sales>();
			Iterator<Menu> itr = menuList.iterator();

			while (itr.hasNext()) {
				Menu menu = itr.next();
				menuName = menu.name;
				price = menu.price;
				qty = getQtyPerMenu(date, menu);
				sales = price * qty;
				list.add(new Sales(date, menuName, price, qty, sales));
			}
			
			TeamFiles.saveExcel(list, "C:\\temp\\Sales.xlsx");
			//TeamFiles.saveExcel(list, "Sales.xlsx");
		}

		/*
		 * @method name : save
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 권예지
		 *
		 * @description : Object 파일 save
		 *
		 * @parameters : Object object, String pathFile
		 *
		 * @return : void
		 */
		public static void save(Object object, String pathFile) { 
			TeamLogger.info("save");
			TeamFiles.saveObject(object, pathFile);
		}
		
		/*
		 * @method name : load
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 권예지
		 *
		 * @description : Object 파일 load
		 *
		 * @parameters : String pathFile
		 *
		 * @return : void
		 */
		public static Pos load(String pathFile) {
			TeamLogger.info("load");
			Pos pos=null;
			File file = new File(pathFile);
			if (file.exists()) {
				System.out.println("로드 : ");
				pos = (Pos) TeamFiles.loadObject(pathFile);
			}
			return pos;

		}

	}
