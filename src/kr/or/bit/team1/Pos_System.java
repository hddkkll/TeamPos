package kr.or.bit.team1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


import kr.or.bit.team1.util.TeamDate;
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
	List<Menu> menuList = new ArrayList<Menu>();
	Customers customers = new Customers();
    static int tableCount = 9; //tableCount의 default 값은 9!
	
	// log경로 설정
	Pos() {
		TeamLogger.getLogger(logPath);
		TeamLogger.info("POS System v0.1");
		TeamLogger.info("@copyleft TeamSoft 2019");

		// load(date);
	}
	public void totalsales() {
         String num = "";
        String date = null;
        System.out.println("\n<<매출관리 메뉴>>");
        System.out.println("1.메뉴 별 매출");
        System.out.println("2.결제 별 매출");
        System.out.println("3.엑셀로 저장");
        System.out.print("선택 : ");
        num = sc.nextLine();
        switch (num) {
        case "1":
            
            printSalesMenu();
            break;
        case "2":
            System.out.print("결제 방식 입력(카드or현금) : ");
            date = sc.nextLine();
            printSalesPayment(date);
            break;
        case "3":
            System.out.println("엑셀로 저장할 매출을 선택하세요");
            exportToExcel(date);
            break;
        default:
        }}

	public void viewTable(int tableCount) {
		
	
		
		for (int i = 1; i <= tableCount; i++) {
			
			
//	   if(tables.tablelist.get(i).orderlist.isEmpty()) {	
//		   tables.tablelist.remove(i);
		  
			
			
		 if(tables.tablelist.get(i) != null ) {
			 if(tables.tablelist.get(i).orderlist.isEmpty()) {
				 System.out.printf("[%d □]", i);
				 
			 }else {
			 System.out.printf("[%d ■]", i);}
		 }else {
			 
			 System.out.printf("[%d □]", i);
			 
		 }
			 
			if (i % 3 == 0) {
				System.out.println();
			}}
			System.out.println();
		     System.out.println("■: 손님이 있는 테이블 , □: 빈 테이블");

		}
	     
	

	

	void menuManage() {

		while (true) {
			System.out.println(menuList.toString());

			String menuNum = "";
			String menuName = null;
			String oldName = null;

			int menuPrice = 0;
			System.out.println("메뉴를 추가하거나 수정할 수 있습니다.");
			System.out.println("1.메뉴추가");
			System.out.println("2.메뉴수정");
			System.out.println("3.메뉴삭제");
			System.out.println("4.종료");
			System.out.println("원하는 번호를 입력하세요");

			menuNum = sc.nextLine();
			switch (menuNum) {

			case "1":
				System.out.println("메뉴이름: ");
				menuName = sc.nextLine();

				System.out.println("가격:");
				menuPrice = Integer.parseInt(sc.nextLine());

				addMenu(menuName, menuPrice);
				break;
			case "2":
				System.out.println("수정할 메뉴를 입력하세요 ");
				oldName = sc.nextLine();
				

				System.out.println("새로운 메뉴를 입력하세요 ");
				menuName = sc.nextLine();

				System.out.println("새로운 가격을 입력하세요 ");
				menuPrice = Integer.parseInt(sc.nextLine());

				modifyMenu(oldName, menuName, menuPrice);
				break;

			case "3":
				System.out.println("삭제할 메뉴를 입력하세요: ");

				menuName = sc.nextLine();

				deleteMenu(menuName);
				break;

			case "4":
				posStart();

			default:
				System.out.println("다시 입력하세요");

			}

		}

	}
	void memberManage() {
        while (true) {
            String num = "";
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
            num = sc.nextLine();

            switch (num) {
            case "0":
                return;
                
            case "1":
                System.out.println("추가할 회원의 번호를 입력해주세요");
                phoneNumber = sc.nextLine();
                addMembers(phoneNumber);
                break;

            case "2":
                System.out.println("회원정보를 수정합니다");
                System.out.println("oldPhoneNumber를 입력해주세요");
                oldphoneNumber = sc.nextLine();
                System.out.println("새로운 PhoneNumber를 입력해주세요");
                phoneNumber = sc.nextLine();

                modifyMembers(oldphoneNumber, phoneNumber);
                break;

            case "3":
                System.out.println("조회할 전화번호를 입력해주세요");
                phoneNumber = sc.nextLine();
                findMembers(phoneNumber);
                break;

            case "4":
                System.out.println("탈퇴할 전화번호를 입력해주세요");
                phoneNumber = sc.nextLine();
                deleteMembers(phoneNumber);
                break;

            case "5":
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
	while(true) {
		  viewTable(tableCount);
		  String tableSelect = "";
		  //int tableNum = Integer.parseInt(tableSelect);
		  System.out.println();
		  System.out.println("테이블을 선택하세요!  0번: 뒤로가기");
		  tableSelect = sc.nextLine();
		  int tableNum = Integer.parseInt(tableSelect);
		  
		  if(tableNum==0) {
			  return;
		  }
		 if(tables.tablelist.get(tableNum)==null) {
		  createTable( tableNum );
		 }
		  showMenu(tableNum);
		
		 //System.out.println(orderlist.listOrders()); listOrders(): 반환값을 스트링으로 리턴해줄 것 ! 
	}
		 
		 }
	
	public int displayMenu() {
		 String menuSelect ="";
			System.out.println("=====POS SYSTEM=====");
			System.out.println();
			System.out.println("1.판매관리");
			System.out.println("2.매출관리");
			System.out.println("3.회원관리");
		    System.out.println("4.메뉴관리");
		    System.out.println("5.테이블관리");
		    System.out.println("6.시스템종료");
		    
		    System.out.print("번호를 입력하세요:");
		    menuSelect = sc.nextLine();
		    int menuNum = Integer.parseInt(menuSelect);
		return menuNum;
	}

	 public void posStart() {
		
	 while(true) { 
	   switch(displayMenu()) {
	    
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
	          
	    case 6 : 
	    	System.exit(0);
	    	
	   }	
	 }
		
	}
	 void createTable(int tableNum) {
		 
			 
		 orderList = new Bucket();
		  tables.tablelist.put(tableNum,orderList);
	 }
	
	 void showMenu(Integer tableNum) {
		  String menuSelect = ""; 
		  int menuNum =0;
		  
		  
		 
		   
		a: while(true) {
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
			 
			 menuSelect = sc.nextLine();
			 menuNum = Integer.parseInt(menuSelect);

			 while(menuNum<0 || menuNum>8) {
				 System.out.println("잘못입력하셨습니다. 다시 입력하세요");
				 menuSelect = sc.nextLine();
			 }
			 
			 
			 switch(menuSelect) {
			 case "0" :
				  
			    break a;
			 
			 case "1" :
				
			        System.out.println("======== MENU ========");
			        for(int i =0; i< menuList.size();i++) {
			        	System.out.printf("%d. %s : %d 원\n",i+1,menuList.get(i).name,menuList.get(i).price);
			        	
			        }
		       
		       while(true) { 
		    	   System.out.println("메뉴를 선택하세요. 뒤로가기는 0번");	   
		    	  
		    	   
		    	 int choiceNum = Integer.parseInt(sc.nextLine());
		    	// System.out.println( menuList.get(choiceNum -1));
		    	 if(choiceNum == 0) {
			           break;
			        }
		    	  
		    	 tables.tablelist.get(tableNum).orderlist.add(new Orders(getMenu(menuList.get(choiceNum -1).name)));
		    	  menuList.get(choiceNum-1).salesCnt++;
		    	 System.out.println(menuList.get(choiceNum -1).name+"이(가) 주문되었습니다. "); 
		    			  
		    		  
		    		  
		    	  }
		       
		    	     break;
		            
			 case "2" :
		    	   System.out.println("취소할 주문을 선택하세요");
		    	   
		    	   
		    	    tables.tablelist.get(tableNum).listOrders();
		    	   
		         
		           String menuName2=sc.nextLine();
		           if(menuName2.equals("0")) {
		           	 return;
		           }
	           
		           tables.tablelist.get(tableNum).deleteOrder(tables.tablelist.get(tableNum).getOrder(getMenu(menuName2)));
		           for(int i = 0; i<menuList.size();i++) {
		        	   if(menuList.get(i).name.equals(menuName2)) {
		        		   menuList.get(i).salesCnt--;
		        		   
		        	   }
		        	   
		        	   
		           }
		        	   
		           
		           
		           System.out.println(menuName2+"이(가) 취소되었습니다.");
		        
		        	   
		    	   break;
		     case "3" :
		    	 
		    	 tables.tablelist.get(tableNum).deleteOrderAll();
		    	 System.out.println("모든 주문이 삭제되었습니다.");
		    	 
		    	
		    	 break;
		    	 
		     
		     
		     
		     
		     case "4" :
		          System.out.println("========주문 내역========");
		          if(tables.tablelist.get(tableNum).orderlist.isEmpty()) {
		        	  System.out.println("주문 내역이 없습니다.");
		          }else {
		          tables.tablelist.get(tableNum).listOrders();
		          }
		          break;
		     
		     case "5" :
		    	 if(tables.tablelist.get(tableNum).orderlist.isEmpty()) {
		        	  System.out.println("주문 내역이 없습니다.");
		          }else {
		          tables.tablelist.get(tableNum).listOrders();
		          }
		    	 
		    	 System.out.println("수량을 변경할 메뉴를 선택하세요");
		    	 String menuName3 = sc.nextLine();
		    	 System.out.println("추가하고 싶은 수량을 입력하세요");
		    	 int qty = Integer.parseInt(sc.nextLine());
		        
		    	 for(int i =0; i<menuList.size(); i++){
		    		 if(menuName3.equals(menuList.get(i).name)) {
		           	   tables.tablelist.get(tableNum).changeQty(menuList.get(i),qty);
		              
		                 System.out.println(menuList.get(i).name+"의 수량이 변경되었습니다.");
		             }
		    	 }
		          break;
		    	 
		     case "6":
	                System.out.println("이동할곳의 테이블번호를 입력하세요");
	                int toTable = Integer.parseInt(sc.nextLine());
	                tables.moveTable(tableNum, toTable);
	                break;
	         case "7":
	                System.out.println("합석할 테이블을 선택하세요");
	                toTable = Integer.parseInt(sc.nextLine());
	                tables.mergeTable(tableNum, toTable);
	                
	                break;	 
	                
	         case "8":
	        	   String paySelect = "";
	        
	        	   System.out.println("카드결제와 현금결제중에 선택하세요!");
	        	   System.out.println("1.카드결제");
	        	   System.out.println("2.현금결제");
	        	   System.out.println("3.분할계산");
	        	   paySelect = sc.nextLine();
	        	   int payMenu = Integer.parseInt(paySelect);
                   
	        	   switch(paySelect) {
	        	   
	        	   case "1":
	        		   
	        		      tables.tablelist.get(tableNum).payCardAll(customers);

	        		      tables.tablelist.put(tableNum,new Bucket());
	        		   
	        		   break;
	        		   
	        	   case "2":
	        		   int amount = 0;
	        		   
	        		   System.out.println(tables.tablelist.get(tableNum).orderSum()+"원입니다. 돈을 주세요:");
	        		  amount = Integer.parseInt(sc.nextLine());
	        		   
	        		  tables.tablelist.get(tableNum).payCashAll(amount, customers);
	        		  
                      tables.tablelist.put(tableNum,new Bucket());
	        		   
	        		    break;
	        	   case "3":
	        		   System.out.println();
	        		  tables.tablelist.get(tableNum).payDutch(customers);
	        	       
	        		  tables.tablelist.put(tableNum,new Bucket());
	        		   break;
	        	   }
	        	   
	        	   break;
	        	 
		     }
			 
		 }
			 }
	
	
	 
	 void tableManage() {
			while (true) {
				String num = "";
				System.out.println("테이블 관리 입니다");
				System.out.println("현재 테이블의 개수는 "+ tableCount+"개 입니다.");
				System.out.println("1. 테이블추가");
				System.out.println("2. 테이블삭제");
				System.out.println("0. 종료");
				System.out.println("번호를 입력하세요");
				num = sc.nextLine();

				switch (num) {
				case "0":
					posStart();
				case "1":
					System.out.println("테이블 1개를 추가합니다");
				    
					   tableCount++;
		
					break;
				case "2":
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
	   
	// 주문(테이블)
	public void orderTable(Integer tableNo, Menu menu) { // 일찬님
		// 테이블에 order add
	}

	public void payTableCash(Integer tableNo) {// 이힘찬
		// 테이블에서 order를 하나씩 가져와서 결제함
		for (int i = 0; i < tables.tablelist.get(tableNo).orderlist.size(); i++) {
			String menu = tables.tablelist.get(tableNo).orderlist.get(i).menuItem.name; // 메뉴이름
			int price = tables.tablelist.get(tableNo).orderlist.get(i).menuItem.price; // 가격
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
		int price = tables.tablelist.get(tableNo).orderSum(); // 가격
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
	        for(int i=0; i<menuList.size(); i++)
	            if(menuList.get(i).name.equals(name))
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
	public void printSalesMenu() { // 강기훈
		// 메뉴-수량-금액
	  // Calendar cal = Calendar.getInstance();
		TeamDate today = new TeamDate();
		
	   String date2 = today.DateString(Calendar.getInstance(),".");
       System.out.println("Date: "+date2);
		
		
	  for(int i =0; i<menuList.size();i++) {
		System.out.println("메뉴: "+menuList.get(i).name+"  수량:"+menuList.get(i).salesCnt + " 금액: "+ menuList.get(i).price* menuList.get(i).salesCnt+"원");;
		
	  }
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
		pos.tables = new Table();

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

//		Customers client = new Customers();
//		client.addCustomers("010-1111-1111");
//		client.addCustomers("010-2222-2222");
//		client.addCustomers("010-3333-3333");
//		client.deleCustomers("010-1111-2222");
//		client.findCustomers("010-2222-1111");

//		System.out.println(client.toString());

		pos.addMenu("짜장", 5000);
		pos.addMenu("짬뽕", 6000);
		pos.addMenu("우동", 5500);

//		Menu pickMenu = pos.getMenu("짜장");

//		System.out.println(pickMenu.toString());

//		// Order 생성
//		Orders order1 = new Orders(pos.getMenu("짜장"));
//		Orders order2 = new Orders(pos.getMenu("짬뽕"));
//		Orders order3 = new Orders(pos.getMenu("짬뽕"));
//
//		System.out.println(order1.toString());
//
//		// OrderList 생성
//		Bucket orderList = new Bucket();
//		orderList.addOrder(order1);
//		orderList.addOrder(order2);
//		orderList.addOrder(order3);
//
//		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
//		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
//
//		System.out.println("짜장 5개 추가");
//		orderList.changeQty(pos.getMenu("짜장"), 5);
//
//		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
//		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
//
//		System.out.println("짜장 6개 취소");
//		orderList.changeQty(pos.getMenu("짜장"), -6);
//
//		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
//		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
//
//		System.out.println("우동 3개 추가");
//		orderList.changeQty(pos.getMenu("우동"), 3);
//
//		System.out.println("짜장 주문수 : " + orderList.menuQty(pos.getMenu("짜장")));
//		System.out.println("짬뽕 주문수 : " + orderList.menuQty(pos.getMenu("짬뽕")));
//		System.out.println("우동 주문수 : " + orderList.menuQty(pos.getMenu("우동")));
//
//		System.out.println(orderList.toString());

//		// Table
//		Table tables = new Table();
//
//		// add table
//		tables.addTable(1);
//		tables.addTable(2);
//		tables.addTable(3);
//		tables.addTable(4);
//		System.out.println(tables.tablelist.toString());
//		// add OrderList to Table
//		tables.addOrderList(1, orderList);
//		System.out.println(tables.tablelist.toString());
//
//		// 결제
//		Customers sonnom = new Customers();
//		sonnom.addCustomers("010-2222-3333");
//		System.out.println("손님 : " + sonnom.customer.toString());
//
//		int yourbill = 20000;
//		// cash
//		Bucket afterLunch = tables.tablelist.get(1);
//		for (int i = 0; i < afterLunch.orderlist.size(); i++) {
//			yourbill -= afterLunch.orderlist.get(i).menuItem.price;
//			// point 적립
//			int new_point = sonnom.customer.get("010-2222-3333")
//					+ (int) (afterLunch.orderlist.get(i).menuItem.price * 0.05);
//			sonnom.customer.put("010-2222-3333", new_point);
//
//		}
//		// 결제완료
//		afterLunch.isPayed = true;
//		System.out.println(yourbill);
//		System.out.println("손님의 포인트 : " + sonnom.customer.toString());
        pos.posStart();
	}
}