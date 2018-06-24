package nl.hu.ipass.techbest.persistence;

import java.sql.Connection;
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

	public boolean deleteOrder(int oid) {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "delete from orders where orders_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, oid);
			pstmt.execute();
			
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
	}
}
