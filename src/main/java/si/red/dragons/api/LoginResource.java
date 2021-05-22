package si.red.dragons.api;

import si.red.dragons.dtos.LoginDTO;
import si.red.dragons.entity.Account;
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
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(LoginDTO login) throws Exception {
        Account account = Account.find("email", login.getEmail()).firstResult();
        if (account == null){
            // User not found
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String hashedPass = CryptoUtils.sha512(login.getPassword());
        if (!hashedPass.equals(account.getPassword())) {
            // Incorrect password
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = JWTUtils.generateToken(account);

        return Response.ok(token).build();
    }
}
