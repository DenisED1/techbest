package nl.hu.ipass.techbest.persistence;

import java.util.List;

import nl.hu.ipass.techbest.model.Product;

public interface ProductDao {
	public List<Product> findAll();
	public Product findByID(int product_ID);
	public List<Product> findByKleur(String kleur);
	public List<Product> findByLeverancier(String lv);
	public List<Product> findByNaam(String nm);
}
