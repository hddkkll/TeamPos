package kr.or.bit.team1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import kr.or.bit.team1.util.TeamFormat;
import kr.or.bit.team1.util.TeamLogger;

public class Customers implements Serializable {

	HashMap<String, Integer> customer;// 키값: 전화번호,
	// 밸류값: 포인트

	public Customers() {
		this.customer = new HashMap<String, Integer>();
	}

	/*
	 * @method name : addCustomers
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 권순조
	 *
	 * @description : 고객을 추가한다.
	 *
	 * @parameters : String phonNumber
	 *
	 * @return : void
	 */
	public void addCustomers(String phoneNumber) {// 권순조
		TeamLogger.info("addCustomers");
		if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
			if (!customer.containsKey(phoneNumber)) {
				customer.put(phoneNumber, 0);
			}
		}

	}

	// 회원등록 OrderList클래스에서 이동받음..
	// FIX : addCustomer와 중복됨. @ deprecated
	public void addMembers(String phoneNumber) {// 신지혁
		customer.put(phoneNumber, 0);
		System.out.println(phoneNumber + "추가 완료");
	}

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
		TeamLogger.info("modifyCustomers");
		if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) { // 핸드폰 정규표현식
			if (customer.containsKey(oldPhoneNumber)) {
				customer.put(phoneNumber, customer.get(oldPhoneNumber)); // 포인트를 새로운 핸드폰으로 옮김
				customer.remove(oldPhoneNumber); // 기존 폰넘버 삭제
			}
		} else {
			System.out.println("핸드폰번호를 확인하고 입력하세요");
		}
	}

	/*
	 * @method name : findCustomers
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 신지혁
	 *
	 * @description : 해당 고객을 찾아 포인트를 보여준다.
	 *
	 * @parameters : String phoneNumber
	 *
	 * @return : HashMap
	 */
	public Customers findCustomers(String phoneNumber) { // 신지혁
		Customers result = new Customers();
		if (customer.get(phoneNumber) != null) {
			System.out.println(phoneNumber + "의 포인트는 : " + customer.get(phoneNumber) + "원 입니다");
			result.customer.put(phoneNumber, customer.get(phoneNumber));
		} else {
			System.out.println("고객이아닙니다");
		}
		return result;
	}

	/*
	 * @method name : deleCustomers
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 이힘찬
	 *
	 * @description : 해당 고객을 삭제한다
	 *
	 * @parameters : String phoneNumber
	 *
	 * @return : void
	 */

	public void deleCustomers(String phoneNumber) {
		TeamLogger.info("deleCustomers");
		if (customer.get(phoneNumber) != null) {
			customer.remove(phoneNumber);
			System.out.println("탈퇴 되었습니다.");
		} else {
			System.out.println("핸드폰번호를 확인하고 입력하세요");
		}
	}

	/*
	 * @method name : listCustomers
	 *
	 * @date : 2019.03.13
	 *
	 * @author : 강기훈
	 *
	 * @description : 고객리스트를 보여준다.
	 *
	 * @parameters :
	 *
	 * @return : void
	 */

	public void listCustomers() {
		TeamLogger.info("listCustomers");
		for (Map.Entry<String, Integer> obj : customer.entrySet()) {
			System.out.println("전화번호:" + obj.getKey() + "/ Point:" + obj.getValue());

		}
	}
	
	public void setPoint(String phoneNumber, int point) {
		if (TeamFormat.iscellPhoneMetPattern(phoneNumber)) {
			if (customer.containsKey(phoneNumber)) {
				customer.put(phoneNumber, point);
			} else {
				System.out.println("현재 가입된 고객이 아닙니다.");
			}
		}
	}

	@Override
	public String toString() {
		return "Customers [customer=" + customer + "]";
	}

}