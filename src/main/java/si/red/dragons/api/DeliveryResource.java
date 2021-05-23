package si.red.dragons.api;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.red.dragons.dtos.DeliveryDTO;
import si.red.dragons.entity.Delivery;
import si.red.dragons.enums.DeliveryStatusEnum;
import si.red.dragons.mappers.DeliveryMapper;
import si.red.dragons.service.GeocodingAPI;
import si.red.dragons.service.RouterAPI;
import si.red.dragons.utils.CarbonEmission;

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

@Path("/delivery")
public class DeliveryResource {
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
    public List<Delivery> getAll() {
        return Delivery.listAll();
    }


    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response create(@Context SecurityContext sc, DeliveryDTO deliveryDTO) throws IOException {
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryDTOToDelivery(deliveryDTO);
        Response json = geocodingAPI.getPoints("json", deliveryDTO.getStartLocCity(), deliveryDTO.getStartLocAddr(), deliveryDTO.getStartLocPostalCode(), APIKey, APIHost);
        JsonValue startLoc = json.readEntity(JsonArray.class).get(0);

        json = geocodingAPI.getPoints("json", deliveryDTO.getEndLocCity(), deliveryDTO.getEndLocAddr(), deliveryDTO.getEndLocPostalCode(), APIKey, APIHost);
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
        delivery.setPoints(fullPath);
        delivery.setStatus(DeliveryStatusEnum.PENDING);
        delivery.setEmissionsSaved(CarbonEmission.calcualteEmission("petrol", 5.4f, (float) path.get("routes").asJsonArray().getJsonObject(0).getJsonNumber("distance").doubleValue()));
        delivery.save();

        return Response.ok().build();
    }


}
