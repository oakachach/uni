package xai.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;
import xai.rest.jettyserver.api.RestServerAPI;
import xai.rest.jettyserver.api.Volum;

/**
 * @author Joan-Manuel Marques
 *
 */

public class RESTclient {

	public double sup(String address, int port, float num_1, float num_2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cylinder/sup/"+num_1+"/"+num_2);
		LSimLogger.log(Level.INFO, "media type: text/plain");
		
		/* COMPLETE CODE */
		
		Response res = operacio("http://"+address+":"+port+"/cylinder/sup/"+num_1+"/"+num_2, "text/plain");
		if (res != null) { 
			return Double.parseDouble(res.readEntity(String.class));
		}
			
		return Double.MIN_VALUE;
	}
	
	public double per(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cylinder/per/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: application/json");
		
		/* COMPLETE CODE */
		Response res = operacio("http://"+address+":"+port+"/cylinder/per/"+value1+"/"+value2, "application/json");
		if (res != null) {
			return Double.parseDouble(res.readEntity(String.class));
		}
		
		return Double.MIN_VALUE;
	}

	public Volum vol(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cylinder/vol/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: application/json");
		
		Volum convertedObject = null;
		
		/* COMPLETE CODE */
		Response res = operacio("http://"+address+":"+port+"/cylinder/vol/"+value1+"/"+value2, "application/json");
		
		if (res != null) {
			return new Gson().fromJson(res.readEntity(String.class), Volum.class);
		}
		
		return null;
	}
	
	private Response operacio(String rest_service_url, String media_type) {
		Client client = ClientBuilder.newClient();
		
		return client.target(rest_service_url).request(media_type).get();
	}
	
}
