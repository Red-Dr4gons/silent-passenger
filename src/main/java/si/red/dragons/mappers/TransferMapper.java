package si.red.dragons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.red.dragons.dtos.TransferDTO;
import si.red.dragons.entity.Transfer;

@Mapper
public interface TransferMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    TransferDTO transferToTransferDTO(Transfer transfer);

    Transfer transferDTOTotransfer(TransferDTO transferDTO);
}
