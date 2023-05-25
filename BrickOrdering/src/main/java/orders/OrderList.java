package orders;

public interface OrderList {
	
	Long createOrder(int numberOfBricks);
	Order getOrder(Long orderReference);
	Order[] getAllOrders();

}
