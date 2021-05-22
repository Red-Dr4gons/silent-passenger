package si.red.dragons.api;

import si.red.dragons.dtos.RatingDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Rating;
import si.red.dragons.mappers.RatingMapper;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
