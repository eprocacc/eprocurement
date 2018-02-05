package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {StateMapper.class, CountryMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "addressState.id", target = "addressStateId")
    @Mapping(source = "addressCountry.id", target = "addressCountryId")
    AddressDTO toDto(Address address); 

    @Mapping(source = "addressStateId", target = "addressState")
    @Mapping(source = "addressCountryId", target = "addressCountry")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
