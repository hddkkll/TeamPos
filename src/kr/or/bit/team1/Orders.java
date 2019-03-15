package kr.or.bit.team1;

import java.io.Serializable;
import java.util.Date;

import kr.or.bit.team1.util.TeamFormat;
import kr.or.bit.team1.util.TeamLogger;

public class Orders implements Serializable {
	static Long orderId = 0L;
	Date orderDate;
	Menu menuItem;
	// OrderStatus orderStatus;

	public Orders(Menu menuItem) {
		TeamLogger.info("Orders :" + menuItem.name);
		orderId++;
		this.orderDate = new Date();
		this.menuItem = menuItem;
		// this.orderStatus = OrderStatus.ORDER;
	}

	public Orders(Menu menuItem, Payments payment) {
		TeamLogger.info("Orders :" + menuItem.name);
		orderId++;
		this.orderDate = new Date();
		this.menuItem = menuItem;
		// this.orderStatus = OrderStatus.ORDER;
	}

	@Override
	public String toString() {
		return "Orders [orderDate=" + TeamFormat.dateTimeFormat(orderDate) + ", menuItem=" + menuItem + "]";
	}

}
