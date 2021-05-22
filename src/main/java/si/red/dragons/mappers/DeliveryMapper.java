package si.red.dragons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.red.dragons.dtos.DeliveryDTO;
import si.red.dragons.entity.Delivery;

@Mapper
public interface DeliveryMapper {
    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    DeliveryDTO deliveryToDeliveryDTO(Delivery delivery);

    Delivery deliveryDTOToDelivery(DeliveryDTO deliveryDTO);
}
