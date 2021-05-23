package si.red.dragons.api;

import si.red.dragons.utils.CarbonEmission;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/emissions")
public class EmissionsResource {
    @GET
    public Response calculate(@QueryParam("consumption") float consumption,
                              @QueryParam("fule") String fuleType,
                              @QueryParam("kilometers") float kilometers){
        float CO2kg = CarbonEmission.calcualteEmission(fuleType, consumption, kilometers);

        return Response.ok(CO2kg).build();
    }
}
