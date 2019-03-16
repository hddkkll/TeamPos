package kr.or.bit.team1;

import java.io.Serializable;

import kr.or.bit.team1.util.TeamLogger;

public class CashPayments implements Payments, Serializable {
		PayType payType=PayType.CASH;
	
		@Override
		public void pay() { // 일찬님
			TeamLogger.info("CashPayments");
			System.out.println(PayType.CASH);
		}

		@Override
		public PayType getPayType() {
			return payType;
		}
		
}