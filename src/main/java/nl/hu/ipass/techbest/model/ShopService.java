package nl.hu.ipass.techbest.model;

import java.util.List;

import nl.hu.ipass.techbest.persistence.OrderDao;
import nl.hu.ipass.techbest.persistence.OrderItemDao;
import nl.hu.ipass.techbest.persistence.OrderItemPostgresDaoImpl;
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

	public Order createOrder(Order order) {
		OrderDao orderDao = new OrderPostgresDaoImpl();
		
		return orderDao.createOrder(order);
	}
	
	public OrderItem createOrderItem(OrderItem item, int order_id, int product_id, double totaal) {
		OrderItemDao itemDao = new OrderItemPostgresDaoImpl();
		
		return itemDao.createOrderItem(item, order_id, product_id, totaal);
	}

	public List<OrderItem> getProductByOrder(int oid) {
		OrderItemDao itemDao = new OrderItemPostgresDaoImpl();

		return itemDao.findByOrder(oid);
	}

	public boolean verwijderItem(int iid) {
		OrderItemDao itemDao = new OrderItemPostgresDaoImpl();
		
		return itemDao.verwijderItem(iid);
	}

	public boolean verwijderOrder(int oid) {
		OrderItemDao itemDao = new OrderItemPostgresDaoImpl();
		
		return itemDao.verwijderOrder(oid);
	}
	
}
