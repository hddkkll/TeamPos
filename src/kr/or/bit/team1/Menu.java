package kr.or.bit.team1;

import java.io.Serializable;

import kr.or.bit.team1.util.TeamLogger;

public class Menu implements Serializable {
		String name;
		int price;
		int salesCnt; 
	

		public Menu(String name, int price) {
			TeamLogger.info("Menu(String name, int price)");
			this.name = name;
			this.price = price;
			this.salesCnt = 0;
		
		}

		@Override
		public String toString() {
			return "Menu [name=" + name + ", price=" + price +"salesCnt= "+salesCnt+ "]";
		}
	}
