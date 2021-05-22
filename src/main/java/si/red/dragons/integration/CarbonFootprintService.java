package si.red.dragons.integration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RegisterRestClient(configKey = "carbon-footprint-api")
@ClientHeaderParam(name = "x-rapidapi-key", value = "{getApiKey}")
@ClientHeaderParam(name = "x-rapidapi-host", value = "carbonfootprint1.p.rapidapi.com")
@ClientHeaderParam(name = "useQueryString", value = "true")
public interface CarbonFootprintService {

    default String getApiKey() {
        final Config config = ConfigProvider.getConfig();
        return config.getValue("carbonFootprint.apiKey", String.class);
    }

    @GET
    @Produces("application/json")
    @Path("/CarbonFootprintFromCarTravel")
    String getCarbonFootprint(@QueryParam("distance") String distance, @QueryParam("vehicle") String vehicle);

}
