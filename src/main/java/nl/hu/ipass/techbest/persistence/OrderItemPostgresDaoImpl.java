package nl.hu.ipass.techbest.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.techbest.model.OrderItem;
import nl.hu.ipass.techbest.model.Product;

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
			double totaal = dbResultSet.getDouble("totaal");
			
			item.setItem_id(itemID);
			item.setOrder_id(orderID);
			item.setProduct_id(productID);
			item.setTotaal(totaal);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return item;
	}
	
	public List<OrderItem> findByOrder(int oid) {
		List<OrderItem> results = new ArrayList<OrderItem>();
		
		try (Connection con = super.getConnection()) {
			String query = "select * from orderItem where orders_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, oid);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			ProductDao productDao = new ProductPostgresDaoImpl();
			
			while (dbResultSet.next()) {
				Long itemID = dbResultSet.getLong("item_id");
				int productID = dbResultSet.getInt("product_id");
				int orderID = dbResultSet.getInt("orders_id");
				int aantal = dbResultSet.getInt("aantal");
				double totaal = dbResultSet.getDouble("totaal");
			
				Product product = productDao.findByID(productID);
				results.add(new OrderItem(itemID, productID, orderID, aantal, totaal, product));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}

	public boolean verwijderItem(int iid) {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "delete from orderItem where item_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, iid);
			pstmt.execute();
			
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
	}

	public boolean verwijderOrder(int oid) {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "delete from orderItem where orders_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, oid);
			pstmt.execute();
			
			OrderDao oDao = new OrderPostgresDaoImpl();
			oDao.deleteOrder(oid);
			
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
	}
}
