package kr.or.bit.team1;



public class Pos_System {
	public static void main(String[] args) {
		Pos pos = new Pos();
		pos=Pos.load("c:\\Temp\\Pos.obj"); // posStart() 시작전
		pos.posStart();
		Pos.save(pos, "c:\\Temp\\Pos.obj"); // posStart() 종료전
		
		
		
		
	}
}