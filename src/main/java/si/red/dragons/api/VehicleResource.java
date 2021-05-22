package si.red.dragons.api;

import si.red.dragons.dtos.VehicleDTO;
import si.red.dragons.entity.Vehicle;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vehicle")
public class VehicleResource {
    @GET
    public List<Vehicle> getAll() {
        return Vehicle.listAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user"})
    public Vehicle getById(@PathParam("id") String vehicleId) {
        return Vehicle.findById(vehicleId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response createVehicle(VehicleDTO vehicle){

        return Response.ok().build();
    }
}
