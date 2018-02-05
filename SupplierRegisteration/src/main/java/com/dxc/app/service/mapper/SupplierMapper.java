package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.SupplierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Supplier and its DTO SupplierDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface SupplierMapper extends EntityMapper<SupplierDTO, Supplier> {

    @Mapping(source = "supplierAddress.id", target = "supplierAddressId")
    SupplierDTO toDto(Supplier supplier); 

    @Mapping(source = "supplierAddressId", target = "supplierAddress")
    @Mapping(target = "supplierToNominees", ignore = true)
    @Mapping(target = "supplierToGeneralInfos", ignore = true)
    Supplier toEntity(SupplierDTO supplierDTO);

    default Supplier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Supplier supplier = new Supplier();
        supplier.setId(id);
        return supplier;
    }
}
