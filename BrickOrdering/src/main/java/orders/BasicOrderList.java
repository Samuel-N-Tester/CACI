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
	public int getOrder(Long orderReference) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BasicOrder getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
