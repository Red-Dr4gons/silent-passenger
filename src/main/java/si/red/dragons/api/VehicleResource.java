package si.red.dragons.api;

import si.red.dragons.dtos.VehicleDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Vehicle;
import si.red.dragons.mappers.VehicleMapper;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/vehicle")
public class VehicleResource {

    @GET
    @RolesAllowed({"user"})
    public List<Vehicle> getByUser(@Context SecurityContext sc) {
        String email = sc.getUserPrincipal().getName();
        return Vehicle.list("account.email", email);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    @Transactional
    public Response createVehicle(@Context SecurityContext sc, VehicleDTO vehicleDTO) {
        String email = sc.getUserPrincipal().getName();
        Account account = Account.find("email", email).firstResult();
        Vehicle vehicle = VehicleMapper.INSTANCE.vehicleDTOToVehicle(vehicleDTO);
        vehicle.setAccount(account);
        vehicle.save();
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{plate}")
    @Transactional
    public void delete(@PathParam("plate") String plate) {
        Vehicle vehicle = Vehicle.find("plate", plate).firstResult();
        if (vehicle != null) {
            vehicle.delete();
        }
    }
}
