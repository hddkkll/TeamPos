package kr.or.bit.team1;

import java.io.Serializable;

import kr.or.bit.team1.util.TeamLogger;

public class CashPayments implements Payments, Serializable {
		@Override
		public void pay() { // 일찬님
			TeamLogger.info("CashPayments");
			System.out.println(PayType.CASH);
		}

}
