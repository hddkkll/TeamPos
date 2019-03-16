package kr.or.bit.team1;

public interface Payments {
//		현금
//		카드
//		분할계산
		public void pay();
		
		public PayType getPayType();
}