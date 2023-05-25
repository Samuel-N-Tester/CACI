package orders;

public class AdvanceOrder implements Order{
	
	Long orderReference;
	int numberOfBricks;
	OrderStatus orderStatus;
	
	public AdvanceOrder(Long orderReference, int numberOfBricks) {
		this.orderReference = orderReference;
		this.numberOfBricks = numberOfBricks;
		this.orderStatus = OrderStatus.ORDERED;
	}
	
	public AdvanceOrder(Long orderReference, int numberOfBricks, OrderStatus orderStatus) {
		this.orderReference = orderReference;
		this.numberOfBricks = numberOfBricks;
		this.orderStatus = orderStatus;
	}
	
	public Long getOrderReference() {
		return orderReference;
	}
	
	public void setOrderReference(Long orderReference) {
		this.orderReference = orderReference;
	}
	
	public int getNumberOfBricks() {
		return numberOfBricks;
	}
	
	public void setNumberOfBricks(int numberOfBricks) {
		this.numberOfBricks = numberOfBricks;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
