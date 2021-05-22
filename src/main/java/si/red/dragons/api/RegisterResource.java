package si.red.dragons.api;


import si.red.dragons.dtos.AccountDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.mappers.AccountMapper;
import si.red.dragons.utils.JWTUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
public class RegisterResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doRegistration(AccountDTO accountDTO) throws Exception {
        Account account = Account.find("email", accountDTO.getEmail()).firstResult();
        if (account != null){
            // User already exists
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Account new_account = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);
        account.save();

        String token = JWTUtils.generateToken(account);

        return Response.ok(token).build();
    }
}
