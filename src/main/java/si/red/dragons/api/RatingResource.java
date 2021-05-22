package si.red.dragons.api;

import si.red.dragons.dtos.RatingDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Rating;
import si.red.dragons.mappers.RatingMapper;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Set;

@Path("/rating")
public class RatingResource {

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response add(@Context SecurityContext sc, RatingDTO ratingDTO) {
        Rating rating = RatingMapper.INSTANCE.ratingDTOToRating(ratingDTO);
        String email = sc.getUserPrincipal().getName();

        rating.setAccountFrom(Account.find("email", email).firstResult());
        rating.setAccountTo(Account.find("email", ratingDTO.getEmail()).firstResult());

        rating.save();

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Integer getRating(@PathParam("id") Long accountId) {

        Account account = Account.findById(accountId);
        Set<Rating> ratings = account.getRatings();

        try {

            Integer averageRating = ratings.stream()
                    .map(r -> r.getValue())
                    .reduce(0, (a, b) -> a + b) / ratings.size();

            return averageRating;

        } catch (ArithmeticException e) {
            return 0;
        }
    }

}
