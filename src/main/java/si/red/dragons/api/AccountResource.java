package si.red.dragons.api;

import si.red.dragons.dtos.AccountDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.mappers.AccountMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/account")
public class AccountResource {
    @GET
    public List<Account> getAll() {
        return Account.listAll();
    }

    @GET
    @Path("/{id}")
    public Account getById(@PathParam("id") Long acccountId) {
        return Account.findById(acccountId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountDTO accountDTO){
        Account account = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);
        account.save();
        return Response.ok().build();
    }
}
