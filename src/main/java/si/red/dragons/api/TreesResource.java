package si.red.dragons.api;

import si.red.dragons.utils.Trees;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/trees")
public class TreesResource {
    @GET
    public Response calculate(@QueryParam("CO2kg") float CO2kg){
        float trees = Trees.treesFromEmissions(CO2kg);

        return Response.ok(trees).build();
    }
}
