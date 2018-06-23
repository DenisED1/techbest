package nl.hu.ipass.techbest.persistence;

import nl.hu.ipass.techbest.model.Order;

public interface OrderDao {
	public Order createOrder(Order order);
	public Order findById(Long order_id);
}
