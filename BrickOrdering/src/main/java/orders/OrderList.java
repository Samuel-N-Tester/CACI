package orders;

public interface OrderList {
	
	Long createOrder(int numberOfBricks);
	int getOrder(Long orderReference);
	BasicOrder getAllOrders();

}
