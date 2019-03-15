package kr.or.bit.team1;

import java.io.Serializable;

import kr.or.bit.team1.util.TeamLogger;

public class CardPayments implements Payments, Serializable {
	PayType payType=PayType.CARD;
	
	@Override
	public void pay() {// 신지혁
		TeamLogger.info("CardPayments");
		System.out.println(PayType.CARD);
		System.out.println("카드계산 입니다...");
	}
	
	@Override
	public PayType getPayType() {
		return payType;
	}
}
