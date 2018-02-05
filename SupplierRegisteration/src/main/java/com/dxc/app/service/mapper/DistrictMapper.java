package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.DistrictDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity District and its DTO DistrictDTO.
 */
@Mapper(componentModel = "spring", uses = {StateMapper.class})
public interface DistrictMapper extends EntityMapper<DistrictDTO, District> {

    @Mapping(source = "districtsState.id", target = "districtsStateId")
    @Mapping(source = "state.id", target = "stateId")
    DistrictDTO toDto(District district); 

    @Mapping(target = "districtTaluqs", ignore = true)
    @Mapping(source = "districtsStateId", target = "districtsState")
    @Mapping(source = "stateId", target = "state")
    District toEntity(DistrictDTO districtDTO);

    default District fromId(Long id) {
        if (id == null) {
            return null;
        }
        District district = new District();
        district.setId(id);
        return district;
    }
}
