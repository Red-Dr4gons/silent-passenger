package si.red.dragons.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "router")
public interface RouterAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{path}")
    Response getPath(
            @PathParam("path") String path,
            @QueryParam("overview") String overview,
            @QueryParam("geometries") String geometries,
            @QueryParam("steps") String steps
    );
}
