package orders;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BasicOrderList implements OrderList {
	
	HashMap<Long, BasicOrder> orders = new HashMap<Long, BasicOrder>();
	AtomicLong nextOrderReference = new AtomicLong(1);

	@Override
	public Long createOrder(int numberOfBricks) {
		Long orderReference = nextOrderReference.getAndIncrement();
		BasicOrder basicOrder = new BasicOrder(orderReference, numberOfBricks);
		orders.put(orderReference, basicOrder);
		return orderReference;
	}

	@Override
	public BasicOrder getOrder(Long orderReference) {
		return orders.get(orderReference);
	}

	@Override
	public BasicOrder[] getAllOrders() {
		return orders.values().toArray(new BasicOrder[0]);
	}

}
