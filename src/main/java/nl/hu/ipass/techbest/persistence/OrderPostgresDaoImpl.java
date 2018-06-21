package nl.hu.ipass.techbest.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.hu.ipass.techbest.model.Order;

public class OrderPostgresDaoImpl extends PostgresBaseDao implements OrderDao {
	public boolean createOrder(Order order) {
		Order result = null;
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("insert into orders (klant_id, bestelDatum, leverDatum) values(1, current_date, current_date + integer '4')");
			pstmt.execute();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		result = findById(order.getOrder_id());
		
		return result.equals(order);
	}

	public Order findById(int order_id) {
		Order order = null;
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM orders WHERE orders_id = ?");
			pstmt.setInt(1, order_id);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			dbResultSet.next();
			int orderID = dbResultSet.getInt("orders_id");
			int klantID = dbResultSet.getInt("klant_id");
			Date bestelDatum = dbResultSet.getDate("besteldatum");
			Date leverDatum = dbResultSet.getDate("leverdatum");
			
			order = new Order(orderID, klantID, bestelDatum, leverDatum);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return order;
	}
	
	
}
