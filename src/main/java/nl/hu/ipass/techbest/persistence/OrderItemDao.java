package nl.hu.ipass.techbest.persistence;

import java.util.List;

import nl.hu.ipass.techbest.model.OrderItem;

public interface OrderItemDao {
	public OrderItem createOrderItem(OrderItem item, int order_id, int product_id, double tt);
	public List<OrderItem> findByOrder(int oid);
}
