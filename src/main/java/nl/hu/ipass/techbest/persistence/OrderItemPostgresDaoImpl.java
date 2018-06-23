package nl.hu.ipass.techbest.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hu.ipass.techbest.model.OrderItem;

public class OrderItemPostgresDaoImpl extends PostgresBaseDao implements OrderItemDao {
	public OrderItem createOrderItem(OrderItem item, int order_id, int product_id, double tt) {
		try (Connection con = super.getConnection()) {
			String query = "insert into orderItem (orders_id, product_id, aantal, totaal) values(?, ?, 1, ?)";
			PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, order_id);
			pstmt.setInt(2, product_id);
			pstmt.setDouble(3, tt);
			pstmt.execute();
			
			ResultSet dbResultSet = pstmt.getGeneratedKeys();

			dbResultSet.next();
			Long itemID = dbResultSet.getLong(1);
			int orderID = dbResultSet.getInt("orders_id");
			int productID = dbResultSet.getInt("product_id");
			
			item.setItem_id(itemID);
			item.setOrder_id(orderID);
			item.setProduct_id(productID);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return item;
	}

}
