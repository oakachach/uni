package xai.rest.jettyserver.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

import xai.rest.utils.*;

/**
 * @author Joan-Manuel Marques
 *
 */
@Path("/cylinder")
public class RestServerAPI {

	private static final double PI = 3.1416;
	
	/**
	 * Calculates cylinder's surface
	 * @param num_1
	 * @param num_2
	 * @return
	 */
	@GET
	@Path("/sup/{num_1}/{num_2}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sup(@PathParam("num_1") float rad, @PathParam("num_2") float alt) {
		LSimLogger.log(Level.INFO, "sup");
		LSimLogger.log(Level.INFO, "rad (num_1): "+rad);
		LSimLogger.log(Level.INFO, "alt (num_2): "+alt);		

		double result = Integer.MIN_VALUE;
		
		/* COMPLETE CODE */	
		result = 2 * PI * rad * (rad + alt);

		LSimLogger.log(Level.INFO, "response: "+(new Double(result)).toString());
		return (new Double(result)).toString();
	}

	/**
	 * Calculates cylinder's perimeter
	 * @param num_1
	 * @param num_2
	 */
	@GET
	@Path("/per/{num_1}/{num_2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String per(@PathParam("num_1") float rad, @PathParam("num_2") float alt) {
		LSimLogger.log(Level.INFO, "per");
		LSimLogger.log(Level.INFO, "rad (num_1): "+rad);
		LSimLogger.log(Level.INFO, "alt (num_2): "+alt);		

		Object obj = null;
		Gson gson = null;

		double result = 2 * alt + 4 * PI * rad;
		
		/* COMPLETE CODE */
		gson = new Gson();
		obj = Double.valueOf(result);

		LSimLogger.log(Level.INFO, "response: "+gson.toJson(obj));
		return gson.toJson(obj);
	}

	/**
	 * Calculates cylinder's volume
	 * @param num_1
	 * @param num_2
	 * @return a json object containing the parameters and the result
	 */
	@GET
	@Path("/vol/{num_1}/{num_2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String vol (@PathParam("num_1") float rad, @PathParam("num_2") float alt) {
		LSimLogger.log(Level.INFO, "vol");
		LSimLogger.log(Level.INFO, "rad (num_1): "+rad);
		LSimLogger.log(Level.INFO, "alt (num_2): "+alt);			
		
		Volum result = null;
		Gson gson = null;
	
		/* COMPLETE CODE */	
		gson = new Gson();
		result = new Volum(rad, alt);
	
		LSimLogger.log(Level.INFO, "response: "+gson.toJson(result));
		return gson.toJson(result);
	}
}
