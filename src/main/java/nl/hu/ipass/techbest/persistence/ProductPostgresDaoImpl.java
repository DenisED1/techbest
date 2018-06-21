package nl.hu.ipass.techbest.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.techbest.model.Product;

public class ProductPostgresDaoImpl extends PostgresBaseDao implements ProductDao {
	
	public List<Product> findAll() {
	List<Product> results = new ArrayList<Product>();
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM product");
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				int productID = dbResultSet.getInt("product_id");
				String naam = dbResultSet.getString("naam");
				String kleur = dbResultSet.getString("kleur");
				String leverancier = dbResultSet.getString("leverancier");
				double prijs = dbResultSet.getDouble("prijs");
				String processor = dbResultSet.getString("processor");
				String geheugen = dbResultSet.getString("geheugen");
				String opslag = dbResultSet.getString("opslag");
				String videokaart = dbResultSet.getString("videokaart");
				
				results.add(new Product(productID, naam, kleur, leverancier, prijs, processor, geheugen, opslag, videokaart));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	public Product findByID(int product_ID) {
		Product product = null;
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM product WHERE product_id = ?");
			pstmt.setInt(1, product_ID);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			dbResultSet.next();
			int productID = dbResultSet.getInt("product_id");
			String naam = dbResultSet.getString("naam");
			String kleur = dbResultSet.getString("kleur");
			String leverancier = dbResultSet.getString("leverancier");
			double prijs = dbResultSet.getDouble("prijs");
			String processor = dbResultSet.getString("processor");
			String geheugen = dbResultSet.getString("geheugen");
			String opslag = dbResultSet.getString("opslag");
			String videokaart = dbResultSet.getString("videokaart");
			
			product = new Product(productID, naam, kleur, leverancier, prijs, processor, geheugen, opslag, videokaart);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return product;
	}

	public List<Product> findByKleur(String kl) {
		List<Product> results = new ArrayList<Product>();
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM product WHERE kleur = ?");
			pstmt.setString(1, kl);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				int productID = dbResultSet.getInt("product_id");
				String naam = dbResultSet.getString("naam");
				String kleur = dbResultSet.getString("kleur");
				String leverancier = dbResultSet.getString("leverancier");
				double prijs = dbResultSet.getDouble("prijs");
				String processor = dbResultSet.getString("processor");
				String geheugen = dbResultSet.getString("geheugen");
				String opslag = dbResultSet.getString("opslag");
				String videokaart = dbResultSet.getString("videokaart");
			
			results.add(new Product(productID, naam, kleur, leverancier, prijs, processor, geheugen, opslag, videokaart));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}

	public List<Product> findByLeverancier(String lv) {
		List<Product> results = new ArrayList<Product>();
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM product WHERE leverancier = ?");
			pstmt.setString(1, lv);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				int productID = dbResultSet.getInt("product_id");
				String naam = dbResultSet.getString("naam");
				String kleurProduct = dbResultSet.getString("kleur");
				String leverancier = dbResultSet.getString("leverancier");
				double prijs = dbResultSet.getDouble("prijs");
				String processor = dbResultSet.getString("processor");
				String geheugen = dbResultSet.getString("geheugen");
				String opslag = dbResultSet.getString("opslag");
				String videokaart = dbResultSet.getString("videokaart");
			
			results.add(new Product(productID, naam, kleurProduct, leverancier, prijs, processor, geheugen, opslag, videokaart));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}

	public List<Product> findByNaam(String nm) {
		List<Product> results = new ArrayList<Product>();
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM product WHERE naam = ?");
			pstmt.setString(1, nm);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				int productID = dbResultSet.getInt("product_id");
				String naam = dbResultSet.getString("naam");
				String kleurProduct = dbResultSet.getString("kleur");
				String leverancier = dbResultSet.getString("leverancier");
				double prijs = dbResultSet.getDouble("prijs");
				String processor = dbResultSet.getString("processor");
				String geheugen = dbResultSet.getString("geheugen");
				String opslag = dbResultSet.getString("opslag");
				String videokaart = dbResultSet.getString("videokaart");
			
			results.add(new Product(productID, naam, kleurProduct, leverancier, prijs, processor, geheugen, opslag, videokaart));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
}
