package si.red.dragons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.red.dragons.dtos.RatingDTO;
import si.red.dragons.entity.Rating;

@Mapper
public interface RatingMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingDTO ratingToRatingDTO(Rating rating);

    Rating ratingDTOToRating(RatingDTO ratingDTO);
}
