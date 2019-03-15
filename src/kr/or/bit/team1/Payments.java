package kr.or.bit.team1;

import java.io.Serializable;

public interface Payments extends Serializable{
//		현금
//		카드
//		분할계산
		public void pay();
		
		public PayType getPayType();
}
