package si.red.dragons.api;

import si.red.dragons.dtos.TransferDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Transfer;
import si.red.dragons.mappers.TransferMapper;
import si.red.dragons.utils.GetPoints;

import javax.annotation.security.RolesAllowed;
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
        Transfer transfer = TransferMapper.INSTANCE.transferDTOTotransfer(transferDTO);

        String email = sc.getUserPrincipal().getName();
        transfer.setAccount(Account.find("email", email).firstResult());

        List points = new GetPoints().callAPI(transferDTO.getStartLocCity(), transferDTO.getStartLocAddr(), transferDTO.getStartLocPostalCode(),
                transferDTO.getEndLocCity(), transferDTO.getEndLocAddr(), transferDTO.getEndLocPostalCode());

        transfer.setPoints(points.toString());

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
