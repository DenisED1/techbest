package nl.hu.ipass.techbest.webservices;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import nl.hu.ipass.techbest.model.Order;
import nl.hu.ipass.techbest.model.Product;
import nl.hu.ipass.techbest.model.ServiceProvider;
import nl.hu.ipass.techbest.model.ShopService;

@Path("/producten")
public class ShopResource {

	@GET
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
    @Path("/createOrder")
    @Consumes("application/json")
	@Produces("application/json")
    public boolean createOrder(Order order) {
		ShopService service = ServiceProvider.getShopService();
		
        return service.createOrder(order); 
    }
}
