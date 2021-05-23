package si.red.dragons.api;

import si.red.dragons.dtos.DeliveryDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Delivery;
import si.red.dragons.entity.Transfer;
import si.red.dragons.enums.DeliveryStatusEnum;
import si.red.dragons.mappers.DeliveryMapper;
import si.red.dragons.utils.GetPoints;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<Delivery> pendingDeliveries = deliveries.stream()
                .filter(d -> d.getStatus() == DeliveryStatusEnum.PENDING)
                .collect(Collectors.toList());

        return Response.ok(pendingDeliveries).build();
    }


    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response create(@Context SecurityContext sc, DeliveryDTO deliveryDTO) throws IOException {
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryDTOToDelivery(deliveryDTO);

        List points = new GetPoints().callAPI(deliveryDTO.getStartLocCity(), deliveryDTO.getStartLocAddr(), deliveryDTO.getStartLocPostalCode(),
                deliveryDTO.getEndLocCity(), deliveryDTO.getEndLocAddr(), deliveryDTO.getEndLocPostalCode());

        delivery.setPoints(points.toString());

        delivery.save();
        return Response.ok().build();
    }


}
