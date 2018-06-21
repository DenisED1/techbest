package nl.hu.ipass.techbest.model;

public class Product {
	private int productID;
	private String naam;
	private String kleur;
	private String leverancier;
	private double prijs;
	private String processor;
	private String geheugen;
	private String opslag;
	private String videokaart;
	
	public Product() {
	}
	
	public Product(int pid, String nm, String kl, String lc, double ps, String pc, String gg, String op, String vk) {
		productID = pid;
		naam = nm;
		kleur = kl;
		leverancier = lc;
		prijs = ps;
		processor = pc;
		geheugen = gg;
		opslag = op;
		videokaart = vk;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getKleur() {
		return kleur;
	}

	public void setKleur(String kleur) {
		this.kleur = kleur;
	}

	public String getLeverancier() {
		return leverancier;
	}

	public void setLeverancier(String leverancier) {
		this.leverancier = leverancier;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getGeheugen() {
		return geheugen;
	}

	public void setGeheugen(String geheugen) {
		this.geheugen = geheugen;
	}

	public String getOpslag() {
		return opslag;
	}

	public void setOpslag(String opslag) {
		this.opslag = opslag;
	}

	public String getVideokaart() {
		return videokaart;
	}

	public void setVideokaart(String videokaart) {
		this.videokaart = videokaart;
	}
}
