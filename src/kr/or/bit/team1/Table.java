package kr.or.bit.team1;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kr.or.bit.team1.util.TeamLogger;

public class Table implements Serializable {

		HashMap<Integer, Bucket> tablelist;
		Date date;
		boolean isPayed; // @ deprecated

		/*
		 * @method name : Table
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 정일찬
		 *
		 * @description : 주문내역을 메뉴별 수량의 합으로 보여준다
		 *
		 * @parameters :
		 *
		 * @return : 
		 */
		public Table() {
			TeamLogger.info("Table()");
			tablelist = new HashMap<Integer, Bucket>();
			this.date = new Date();
			this.isPayed = false;

		}

		public void showTable() { // 정일찬
		}
		
		

		/*
		 * @method name : addOrderList
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 정일찬
		 *
		 * @description : 테이블에 BucketList 추가
		 *
		 * @parameters : Integer tableNo, Bucket orderList
		 *
		 * @return : void
		 */
		public void addOrderList(Integer tableNo, Bucket orderList) {
			TeamLogger.info("addOrderList(Integer tableNo, Bucket orderList)");
			tablelist.put(tableNo, orderList);
		}

		/*
		 * @method name : moveTable
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 강기훈
		 *
		 * @description : 테이블에 BucketList 추가
		 *
		 * @parameters : int fromTable, int toTable
		 *
		 * @return : void
		 */
		public void moveTable(int fromTable, int toTable) {// 강기훈
			TeamLogger.info("moveTable(int fromTable, int toTable)");
			Bucket temp = new Bucket();
			temp = tablelist.get(fromTable);
			tablelist.put(toTable, temp);
			tablelist.put(fromTable,new Bucket());
		}

		/*
		 * @method name : mergeTable
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 권예지
		 *
		 * @description : 테이블 합치기
		 *
		 * @parameters : int fromTable, int toTable
		 *
		 * @return : void
		 */
		public void mergeTable(int fromTable, int toTable) {// 권예지
			TeamLogger.info("mergeTable(int fromTable, int toTable)");
			if(tablelist.get(fromTable)!=null && tablelist.get(toTable)!=null) {
				Bucket temp = new Bucket();
				temp = tablelist.get(fromTable);
				Bucket temp2 = new Bucket();
				temp2 = tablelist.get(toTable);

				for (int i = 0; i < temp.orderlist.size(); i++)
					tablelist.get(toTable).orderlist.add(temp.orderlist.get(i));
				 
		        for (int i = 0; i < temp.orderlist.size(); i++)
		        	tablelist.get(fromTable).orderlist.remove(i);
			}

		}

		/*
		 * @method name : addTable
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 권순조
		 *
		 * @description : 테이블 추가
		 *
		 * @parameters : int tableNo
		 *
		 * @return : void
		 */
		public void addTable(int tableNo) {
			TeamLogger.info("addTable(int tableNo)");
			this.tablelist.put(tableNo, null);

		}

		/*
		 * @method name : deleteTable
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 강기훈
		 *
		 * @description : 테이블 삭제
		 *
		 * @parameters : int tableNo
		 *
		 * @return : void
		 */
		public void deleteTable(int tableNo) {
			TeamLogger.info("deleteTable(int tableNo)");
			if(tablelist.containsKey(tableNo)) {
				tablelist.remove(tableNo);
				System.out.println("테이블 " + tableNo + "이 삭제되었습니다.");
			}
			
		}

		/*
		 * @method name : availableTable
		 *
		 * @date : 2019.03.14
		 *
		 * @author : 강기훈
		 *
		 * @description : 테이블 초기화 (결제된 테이블을 비워둔다)
		 *
		 * @parameters : 
		 *
		 * @return : void
		 */
		public void availableTable() {
			TeamLogger.info("availableTable");
			for (Map.Entry<Integer, Bucket> obj : tablelist.entrySet()) {
				if (obj.getValue().isPayed) {
					tablelist.put(obj.getKey(), null);
				}
			}
		}

	}