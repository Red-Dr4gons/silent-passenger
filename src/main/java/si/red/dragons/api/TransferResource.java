package si.red.dragons.api;

import si.red.dragons.dtos.TransferDTO;
import si.red.dragons.entity.Transfer;
import si.red.dragons.mappers.TransferMapper;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/transfer")
public class TransferResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transfer> getAll() {
        return Transfer.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer getById(@PathParam("id") Long transferId) {
        return Transfer.findById(transferId);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TransferDTO transferDTO) {
        Transfer transfer = TransferMapper.INSTANCE.transferDTOTotransfer(transferDTO);

        //TODO add account
        transfer.save();

        return Response.ok().build();
    }

    @Transactional
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response delete(@PathParam("id") Long transferId) {

        if (!Transfer.deleteById(transferId)) {
            throw new WebApplicationException(404);
        } else {
            return Response.status(204).build();
        }
    }

}
