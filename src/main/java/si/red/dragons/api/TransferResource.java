package si.red.dragons.api;

import si.red.dragons.entity.Transfer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/account")
public class TransferResource {

    @GET
    public List<Transfer> getAll() {
        return Transfer.listAll();
    }

    @GET
    @Path("/{id}")
    public Transfer getById(@PathParam("id") Long transferId) {
        return Transfer.findById(transferId);
    }

}
