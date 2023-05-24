package orders;

import java.util.HashMap;

public interface OrderList {
	
	Long createOrder(int numberOfBricks);
	BasicOrder getOrder(Long orderReference);
	HashMap<Long, BasicOrder> getAllOrders();

}
