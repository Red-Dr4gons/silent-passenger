package si.red.dragons.api;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.red.dragons.dtos.TransferDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Transfer;
import si.red.dragons.mappers.TransferMapper;
import si.red.dragons.service.GeocodingAPI;
import si.red.dragons.service.RouterAPI;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.List;

@Path("/transfer")
public class TransferResource {
    @ConfigProperty(name = "api.key")
    String APIKey;
    @ConfigProperty(name = "api.apiHost")
    String APIHost;

    @RestClient
    @Inject
    GeocodingAPI geocodingAPI;

    @RestClient
    @Inject
    RouterAPI routerAPI;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public List<Transfer> getAll() {
        return Transfer.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Transfer getById(@PathParam("id") Long transferId) {
        return Transfer.findById(transferId);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response create(@Context SecurityContext sc, TransferDTO transferDTO) throws IOException {
        Account account = Account.find("email", sc.getUserPrincipal().getName()).firstResult();
        Transfer transfer = TransferMapper.INSTANCE.transferDtoTotransfer(transferDTO, account);
        Response json = geocodingAPI.getPoints("json", transferDTO.getStartLocCity(), transferDTO.getStartLocAddr(), transferDTO.getStartLocPostalCode(), APIKey, APIHost);
        JsonValue startLoc = json.readEntity(JsonArray.class).get(0);

        json = geocodingAPI.getPoints("json", transferDTO.getEndLocCity(), transferDTO.getEndLocAddr(), transferDTO.getEndLocPostalCode(), APIKey, APIHost);
        JsonValue endLoc = json.readEntity(JsonArray.class).get(0);

        json = routerAPI.getPath(startLoc.asJsonObject().getString("lat") + "," + startLoc.asJsonObject().getString("lon") + ";" + endLoc.asJsonObject().getString("lat") + "," + endLoc.asJsonObject().getString("lon"),
                "full", "polyline", "true");
        JsonObject path = json.readEntity(JsonObject.class);

        JsonArray steps = path.get("routes").asJsonArray().get(0).asJsonObject().get("legs").asJsonArray().get(0).asJsonObject().get("steps").asJsonArray();

        String fullPath = "";
        for (JsonValue val : steps) {
            for (JsonValue inter : val.asJsonObject().get("intersections").asJsonArray()) {
                JsonArray loc = inter.asJsonObject().getJsonArray("location");
                fullPath += loc.getJsonNumber(0).toString() + "," + loc.getJsonNumber(1).toString();
                fullPath += ";";
            }
        }
        transfer.setPoints(fullPath);
        String email = sc.getUserPrincipal().getName();
        transfer.setAccount(Account.find("email", email).firstResult());

        transfer.save();

        return Response.ok().build();
    }

    @Transactional
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed({"user"})
    public Response delete(@PathParam("id") Long transferId) {

        if (!Transfer.deleteById(transferId)) {
            throw new WebApplicationException(404);
        } else {
            return Response.status(204).build();
        }
    }

}
