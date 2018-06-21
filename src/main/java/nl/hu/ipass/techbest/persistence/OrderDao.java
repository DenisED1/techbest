package nl.hu.ipass.techbest.persistence;

import nl.hu.ipass.techbest.model.Order;

public interface OrderDao {
	public boolean createOrder(Order order);
	public Order findById(int order_id);
}
