package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.TaluqDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Taluq and its DTO TaluqDTO.
 */
@Mapper(componentModel = "spring", uses = {DistrictMapper.class})
public interface TaluqMapper extends EntityMapper<TaluqDTO, Taluq> {

    @Mapping(source = "taluqsDistrict.id", target = "taluqsDistrictId")
    @Mapping(source = "district.id", target = "districtId")
    TaluqDTO toDto(Taluq taluq); 

    @Mapping(source = "taluqsDistrictId", target = "taluqsDistrict")
    @Mapping(source = "districtId", target = "district")
    Taluq toEntity(TaluqDTO taluqDTO);

    default Taluq fromId(Long id) {
        if (id == null) {
            return null;
        }
        Taluq taluq = new Taluq();
        taluq.setId(id);
        return taluq;
    }
}
