package orders;

public class BasicOrder implements Order {
	
	Long orderReference;
	int numberOfBricks;
	
	public BasicOrder(Long orderReference, int numberOfBricks) {
		this.orderReference = orderReference;
		this.numberOfBricks = numberOfBricks;
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

}
