package si.red.dragons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import si.red.dragons.dtos.VehicleDTO;
import si.red.dragons.entity.Vehicle;

@Mapper
public interface VehicleMapper {
    public VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    public VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    public Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);
}
