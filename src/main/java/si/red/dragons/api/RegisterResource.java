package si.red.dragons.api;


import si.red.dragons.dtos.AccountDTO;
import si.red.dragons.dtos.ResponseDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.enums.ResponseStatus;
import si.red.dragons.mappers.AccountMapper;
import si.red.dragons.utils.CryptoUtils;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
public class RegisterResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response doRegistration(AccountDTO accountDTO) {
        Account account = Account.find("email", accountDTO.getEmail()).firstResult();
        ResponseDTO response = new ResponseDTO();
        if (account != null) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage("Email already registered!");
            // User already exists
            return Response.ok(response).build();
        }
        Account newAccount = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);
        newAccount.setPassword(CryptoUtils.sha512(accountDTO.getPassword()));
        newAccount.save();
        response.setStatus(ResponseStatus.SUCCESS);
        return Response.ok(response).build();
    }
}
