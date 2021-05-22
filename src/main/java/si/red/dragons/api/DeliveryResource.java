package si.red.dragons.api;

import si.red.dragons.dtos.DeliveryDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Delivery;
import si.red.dragons.entity.Transfer;
import si.red.dragons.mappers.DeliveryMapper;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Path("/delivery")
public class DeliveryResource {

    @GET
    @Path("/{id}")
    public Response GetPendingDeliveriesByAccount(@PathParam("id") Long idAccount) {

        Account account = Account.find("idAccount", idAccount).firstResult();

        List<Delivery> deliveries = new ArrayList<>();

        for (Transfer t : account.getTransfers()) {
            Set<Delivery> tDeliveries = t.getDeliveries();
            deliveries.addAll(tDeliveries);
        }

        return Response.ok(deliveries).build();
    }


    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response create(@Context SecurityContext sc, DeliveryDTO deliveryDTO) {
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryDTOToDelivery(deliveryDTO);
        String email = sc.getUserPrincipal().getName();

        delivery.save();
        return Response.ok().build();
    }


}
