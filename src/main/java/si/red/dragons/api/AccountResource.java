package si.red.dragons.api;

import si.red.dragons.dtos.AccountDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.mappers.AccountMapper;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/account")
public class AccountResource {
    @GET
    @RolesAllowed({"user"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AccountDTO getByEmail(@Context SecurityContext sc) {
        String email = sc.getUserPrincipal().getName();
        Account acc = Account.find("email", email).firstResult();
        return AccountMapper.INSTANCE.accountToAccountDTO(acc);
    }

    @PUT
    @RolesAllowed({"user"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@Context SecurityContext sc, AccountDTO accountDTO) {
        String email = sc.getUserPrincipal().getName();
        Account acc = Account.find("email", email).firstResult();
        Account newAccount = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);

        acc.setName(newAccount.getName());
        acc.setSurname(newAccount.getSurname());
        acc.setAddress(newAccount.getAddress());
        acc.setPhoneNumber(newAccount.getPhoneNumber());

        acc.save();
        return Response.accepted().build();
    }
}
