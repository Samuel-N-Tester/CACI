package orders;

public interface OrderList {
	
	Long createOrder(int numberOfBricks);
	BasicOrder getOrder(Long orderReference);
	BasicOrder[] getAllOrders();

}
