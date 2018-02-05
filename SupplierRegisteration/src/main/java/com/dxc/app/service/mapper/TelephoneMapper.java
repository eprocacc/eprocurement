package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.TelephoneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Telephone and its DTO TelephoneDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierGeneralInfoMapper.class})
public interface TelephoneMapper extends EntityMapper<TelephoneDTO, Telephone> {

    @Mapping(source = "supplierGeneralInfo.id", target = "supplierGeneralInfoId")
    TelephoneDTO toDto(Telephone telephone); 

    @Mapping(source = "supplierGeneralInfoId", target = "supplierGeneralInfo")
    Telephone toEntity(TelephoneDTO telephoneDTO);

    default Telephone fromId(Long id) {
        if (id == null) {
            return null;
        }
        Telephone telephone = new Telephone();
        telephone.setId(id);
        return telephone;
    }
}
