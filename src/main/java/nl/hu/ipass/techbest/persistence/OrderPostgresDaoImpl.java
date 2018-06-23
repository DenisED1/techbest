package nl.hu.ipass.techbest.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hu.ipass.techbest.model.Order;

public class OrderPostgresDaoImpl extends PostgresBaseDao implements OrderDao {
	public Order createOrder(Order order) {
		try (Connection con = super.getConnection()) {
			String query = "insert into orders (klant_id, bestelDatum, leverDatum) values(1, current_date, current_date + integer '4')";
			PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.execute();
			
			ResultSet dbResultSet = pstmt.getGeneratedKeys();

			dbResultSet.next();
			Long orderID = dbResultSet.getLong(1);
			order.setOrder_id(orderID);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return order;
	}

	public Order findById(Long order_id) {
		Order order = null;
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM orders WHERE orders_id = ?");
			pstmt.setLong(1, order_id);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			dbResultSet.next();
			Long orderID = dbResultSet.getLong("orders_id");
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
