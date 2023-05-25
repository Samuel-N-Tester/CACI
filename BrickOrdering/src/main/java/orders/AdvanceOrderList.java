package orders;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AdvanceOrderList implements OrderList {
	
	HashMap<Long, AdvanceOrder> orders = new HashMap<Long, AdvanceOrder>();
	AtomicLong nextOrderReference = new AtomicLong(1);

	/**
	 * Creates the Order using an AtomicLong as a thread safe non synchronous way to make sure the
	 * Reference is unique
	 * @param int numberOfBricks
	 * @return Long orderReference
	 */
	@Override
	public Long createOrder(int numberOfBricks) {
		Long orderReference = nextOrderReference.getAndIncrement();
		AdvanceOrder advanceOrder = new AdvanceOrder(orderReference, numberOfBricks);
		orders.put(orderReference, advanceOrder);
		return orderReference;
	}

	/**
	 * Gets the order that you requested
	 * @param Long orderReference
	 * @return AdcenceOrder The order you requested
	 */
	@Override
	public AdvanceOrder getOrder(Long orderReference) {
		return orders.get(orderReference);
	}

	/**
	 * Gets all AdcenceOrders that exist
	 * @return AdcenceOrder[] All AdcenceOrders that exist
	 */
	@Override
	public AdvanceOrder[] getAllOrders() {
		return orders.values().toArray(new AdvanceOrder[0]);
	}
	
	/**
	 * Updates a previous made order and then makes a new one
	 * @param orderReference
	 * @param numberOfBricks
	 * @return Long orderReference The reference of the new order
	 */
	public Long updateOrder(Long orderReference, int numberOfBricks) {
		// Create a new order
		Long updatedOrderReference = nextOrderReference.getAndIncrement();
		AdvanceOrder advanceOrder = new AdvanceOrder(updatedOrderReference, numberOfBricks);
		orders.put(updatedOrderReference, advanceOrder);
		
		// Set the old order to updated
		orders.get(orderReference).setOrderStatus(OrderStatus.UPDATED);
		
		return updatedOrderReference;
	}

}
