package si.red.dragons.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "geocoding")
public interface GeocodingAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getPoints(
            @QueryParam("format") String format,
            @QueryParam("city") String city,
            @QueryParam("street") String street,
            @QueryParam("postalcode") String post,
            @HeaderParam("x-rapidapi-key") String key,
            @HeaderParam("x-rapidapi-host") String host
    );
}
