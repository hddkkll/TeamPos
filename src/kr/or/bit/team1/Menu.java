package kr.or.bit.team1;

import java.io.Serializable;

import kr.or.bit.team1.util.TeamLogger;

public class Menu implements Serializable {
		String name;
		int price;

		public Menu(String name, int price) {
			TeamLogger.info("Menu(String name, int price)");
			this.name = name;
			this.price = price;
		}

		@Override
		public String toString() {
			return "Menu [name=" + name + ", price=" + price + "]";
		}
	}
