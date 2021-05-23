package si.red.dragons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.red.dragons.dtos.TransferDTO;
import si.red.dragons.entity.Account;
import si.red.dragons.entity.Transfer;

@Mapper
public interface TransferMapper {
    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    TransferDTO transferToTransferDTO(Transfer transfer);

    Transfer transferDtoTotransfer(TransferDTO transferDto, Account account);
}
