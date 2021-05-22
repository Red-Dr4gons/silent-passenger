package si.red.dragons.api;

import si.red.dragons.dtos.LoginDTO;
import si.red.dragons.dtos.ResponseDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.enums.ResponseStatus;
import si.red.dragons.utils.CryptoUtils;
import si.red.dragons.utils.JWTUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO login) throws Exception {
        Account account = Account.find("email", login.getEmail()).firstResult();
        ResponseDTO response = new ResponseDTO();
        response.setStatus(ResponseStatus.FAILURE);
        response.setMessage("Incorrect password or email!");
        if (account == null) {
            // User not found
            return Response.ok(response).build();
        }
        String hashedPass = CryptoUtils.sha512(login.getPassword());
        if (!hashedPass.equals(account.getPassword())) {
            // Incorrect password
            return Response.ok(response).build();
        }

        String token = JWTUtils.generateToken(account);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage(token);
        return Response.ok(response).build();
    }
}
