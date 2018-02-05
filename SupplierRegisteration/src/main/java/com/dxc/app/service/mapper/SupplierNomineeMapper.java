package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.SupplierNomineeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SupplierNominee and its DTO SupplierNomineeDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface SupplierNomineeMapper extends EntityMapper<SupplierNomineeDTO, SupplierNominee> {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "nomineesToSupplier.id", target = "nomineesToSupplierId")
    SupplierNomineeDTO toDto(SupplierNominee supplierNominee); 

    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "nomineesToSupplierId", target = "nomineesToSupplier")
    SupplierNominee toEntity(SupplierNomineeDTO supplierNomineeDTO);

    default SupplierNominee fromId(Long id) {
        if (id == null) {
            return null;
        }
        SupplierNominee supplierNominee = new SupplierNominee();
        supplierNominee.setId(id);
        return supplierNominee;
    }
}
