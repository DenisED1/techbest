package nl.hu.ipass.techbest.model;

public class OrderItem {
	private Long item_id;
	private int product_id;
	private int order_id;
	private int aantal;
	private double totaal;
	
	public OrderItem(Long iid, int pid, int oid, int an, double tt) {
		item_id = iid;
		product_id = pid;
		order_id = oid;
		aantal = an;
		totaal = tt;
	}

	public Long getItem_id() {
		return item_id;
	}
	
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public int getAantal() {
		return aantal;
	}
	
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	
	public double getTotaal() {
		return totaal;
	}
	
	public void setTotaal(double totaal) {
		this.totaal = totaal;
	}
}
