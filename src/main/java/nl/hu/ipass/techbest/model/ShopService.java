package nl.hu.ipass.techbest.model;

import java.util.List;

import nl.hu.ipass.techbest.persistence.OrderDao;
import nl.hu.ipass.techbest.persistence.OrderPostgresDaoImpl;
import nl.hu.ipass.techbest.persistence.ProductDao;
import nl.hu.ipass.techbest.persistence.ProductPostgresDaoImpl;

public class ShopService {
	public List<Product> getAllProducten() {
		ProductDao productDao = new ProductPostgresDaoImpl();
		return productDao.findAll();
	}
	
	public Product getProductByID(int id) {
		ProductDao productDao = new ProductPostgresDaoImpl();
		
		return productDao.findByID(id);
	}

	public List<Product> getProductByKleur(String kl) {
		ProductDao productDao = new ProductPostgresDaoImpl();
		
		return productDao.findByKleur(kl);
	}

	public List<Product> getProductByLeverancier(String lv) {
		ProductDao productDao = new ProductPostgresDaoImpl();
		
		return productDao.findByLeverancier(lv);
	}

	public List<Product> getProductByNaam(String nm) {
		ProductDao productDao = new ProductPostgresDaoImpl();
		
		return productDao.findByNaam(nm);
	}

	public boolean createOrder(Order order) {
		OrderDao orderDao = new OrderPostgresDaoImpl();
		
		return orderDao.createOrder(order);
	}
	
}
