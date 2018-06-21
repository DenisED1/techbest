package nl.hu.ipass.techbest.model;

public class ServiceProvider {
	private static ShopService shopService = new ShopService();
	
	public static ShopService getShopService() {
		return shopService;
	}
}