package si.red.dragons.api;

import si.red.dragons.dtos.RatingDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Rating;
import si.red.dragons.mappers.RatingMapper;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/rating")
public class RatingResource {

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(RatingDTO ratingDTO) {
        Rating rating = RatingMapper.INSTANCE.ratingDTOToRating(ratingDTO);
        rating.setAccountTo(Account.find("email", ratingDTO.getEmail()).firstResult());
        //TODO add account from
        rating.save();

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
