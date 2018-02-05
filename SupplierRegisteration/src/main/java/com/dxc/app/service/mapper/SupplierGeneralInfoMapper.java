package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.SupplierGeneralInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SupplierGeneralInfo and its DTO SupplierGeneralInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class, AddressMapper.class})
public interface SupplierGeneralInfoMapper extends EntityMapper<SupplierGeneralInfoDTO, SupplierGeneralInfo> {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "supplierGeneralToAddress.id", target = "supplierGeneralToAddressId")
    @Mapping(source = "generalInfosToSupplier.id", target = "generalInfosToSupplierId")
    SupplierGeneralInfoDTO toDto(SupplierGeneralInfo supplierGeneralInfo); 

    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "supplierGeneralToAddressId", target = "supplierGeneralToAddress")
    @Mapping(target = "suppGeneralInfoTelephones", ignore = true)
    @Mapping(source = "generalInfosToSupplierId", target = "generalInfosToSupplier")
    SupplierGeneralInfo toEntity(SupplierGeneralInfoDTO supplierGeneralInfoDTO);

    default SupplierGeneralInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        SupplierGeneralInfo supplierGeneralInfo = new SupplierGeneralInfo();
        supplierGeneralInfo.setId(id);
        return supplierGeneralInfo;
    }
}
