package nl.hu.ipass.techbest.model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private Long order_id;
	private int klant_id;
	private Date bestelDatum;
	private Date leverDatum;
	private ArrayList<OrderItem> orderItems;
	
	public Order(Long oid, int kid, Date bd, Date ld) {
		order_id = oid;
		klant_id = kid;
		bestelDatum = bd;
		leverDatum = ld;
	}
	
	public Long getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public int getKlant_id() {
		return klant_id;
	}

	public void setKlant_id(int klant_id) {
		this.klant_id = klant_id;
	}
	
	public Date getBestelDatum() {
		return bestelDatum;
	}
	
	public void setBestelDatum(Date bestelDatum) {
		this.bestelDatum = bestelDatum;
	}
	
	public Date getLeverDatum() {
		return leverDatum;
	}
	
	public void setLeverDatum(Date leverDatum) {
		this.leverDatum = leverDatum;
	}
	
	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
