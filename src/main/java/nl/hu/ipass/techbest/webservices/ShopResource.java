package nl.hu.ipass.techbest.webservices;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import nl.hu.ipass.techbest.model.Order;
import nl.hu.ipass.techbest.model.OrderItem;
import nl.hu.ipass.techbest.model.Product;
import nl.hu.ipass.techbest.model.ServiceProvider;
import nl.hu.ipass.techbest.model.ShopService;

@Path("/producten")
public class ShopResource {

	@GET
	@Path("getAll")
	@Produces("application/json")
	public String getAllProducten() {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Product>producten = service.getAllProducten();
		
		for (Product p : producten) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("productID", p.getProductID());
			job.add("naam", p.getNaam());
			job.add("kleur", p.getKleur());
			job.add("leverancier", p.getLeverancier());
			job.add("prijs", p.getPrijs());
			job.add("processor", p.getProcessor());
			job.add("geheugen", p.getGeheugen());
			job.add("opslag", p.getOpslag());
			job.add("videokaart", p.getVideokaart());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("id/{id}")
	@Produces("application/json")
	public String getProductByID(@PathParam("id") int id) {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Product p = service.getProductByID(id);
		
		if (p == null) {
			throw new WebApplicationException("No such product!");
		}
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("productID", p.getProductID());
		job.add("naam", p.getNaam());
		job.add("kleur", p.getKleur());
		job.add("leverancier", p.getLeverancier());
		job.add("prijs", p.getPrijs());
		job.add("processor", p.getProcessor());
		job.add("geheugen", p.getGeheugen());
		job.add("opslag", p.getOpslag());
		job.add("videokaart", p.getVideokaart());
		
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("kleur/{kl}")
	@Produces("application/json")
	public String getProductByKleur(@PathParam("kl") String kl) {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Product> producten = service.getProductByKleur(kl);
		
		for (Product p : producten) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("productID", p.getProductID());
			job.add("naam", p.getNaam());
			job.add("kleur", p.getKleur());
			job.add("leverancier", p.getLeverancier());
			job.add("prijs", p.getPrijs());
			job.add("processor", p.getProcessor());
			job.add("geheugen", p.getGeheugen());
			job.add("opslag", p.getOpslag());
			job.add("videokaart", p.getVideokaart());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("leverancier/{lv}")
	@Produces("application/json")
	public String getProductByLeverancier(@PathParam("lv") String lv) {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Product> producten = service.getProductByLeverancier(lv);
		
		for (Product p : producten) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("productID", p.getProductID());
			job.add("naam", p.getNaam());
			job.add("kleur", p.getKleur());
			job.add("leverancier", p.getLeverancier());
			job.add("prijs", p.getPrijs());
			job.add("processor", p.getProcessor());
			job.add("geheugen", p.getGeheugen());
			job.add("opslag", p.getOpslag());
			job.add("videokaart", p.getVideokaart());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("naam/{nm}")
	@Produces("application/json")
	public String getProductByNaam(@PathParam("nm") String nm) {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Product> producten = service.getProductByNaam(nm);
		
		for (Product p : producten) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("productID", p.getProductID());
			job.add("naam", p.getNaam());
			job.add("kleur", p.getKleur());
			job.add("leverancier", p.getLeverancier());
			job.add("prijs", p.getPrijs());
			job.add("processor", p.getProcessor());
			job.add("geheugen", p.getGeheugen());
			job.add("opslag", p.getOpslag());
			job.add("videokaart", p.getVideokaart());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
    @Path("createOrder")
    @Consumes("application/json")
    @Produces("application/json")
    public String createOrder() {
        ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		Order order = new Order(null, 1, null, null);
		Order o = service.createOrder(order);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("orderID", o.getOrder_id());
		
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
    }
	
	@POST
	@Path("createOrderItem")
	@Consumes("application/json")
	@Produces("application/json")
	public String createOrderItem(String stringJson) {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		JSONObject myJson = new JSONObject(stringJson);
		int orderID = myJson.getInt("orderID");
		int productID = myJson.getInt("productID");
		double prijs = myJson.getDouble("prijs");
		
		OrderItem item = new OrderItem(null, 0, 0, 1, 0.0);
		OrderItem i = service.createOrderItem(item, orderID, productID, prijs);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("itemID", i.getItem_id());
		job.add("orderID", i.getOrder_id());
		job.add("productID", i.getProduct_id());
		job.add("totaal", i.getTotaal());
		
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("bekijkOrderItems/{oid}")
	@Produces("application/json")
	public String getProductByOrder(@PathParam("oid") int oid) {
		ShopService service = ServiceProvider.getShopService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<OrderItem> items = service.getProductByOrder(oid);
		
		for (OrderItem i : items) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("itemID", i.getItem_id());
			job.add("orderID", i.getOrder_id());
			job.add("productID", i.getProduct_id());
			job.add("totaal", i.getTotaal());
			job.add("aantal", i.getAantal());
			job.add("naam", i.getProduct().getNaam());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@DELETE
	@Path("verwijderItem/{iid}")
	@Produces("application/json")
	public Response verwijderItem(@PathParam("iid") int iid) {
		ShopService service = ServiceProvider.getShopService();
		
		if (!service.verwijderItem(iid)) {
		      return Response.status(404).build();
		    }

		    return Response.ok().build();
	}
	
	@DELETE
	@Path("verwijderOrder/{oid}")
	@Produces("application/json")
	public Response verwijderOrder(@PathParam("oid") int oid) {
		ShopService service = ServiceProvider.getShopService();
		
		if (!service.verwijderOrder(oid)) {
		      return Response.status(404).build();
		    }

		    return Response.ok().build();
	}
}
