package com.dxc.app.service.mapper;

import com.dxc.app.domain.*;
import com.dxc.app.service.dto.DigitalCertificateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DigitalCertificate and its DTO DigitalCertificateDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface DigitalCertificateMapper extends EntityMapper<DigitalCertificateDTO, DigitalCertificate> {

    @Mapping(source = "digitalCertificateUser.id", target = "digitalCertificateUserId")
    DigitalCertificateDTO toDto(DigitalCertificate digitalCertificate); 

    @Mapping(source = "digitalCertificateUserId", target = "digitalCertificateUser")
    DigitalCertificate toEntity(DigitalCertificateDTO digitalCertificateDTO);

    default DigitalCertificate fromId(Long id) {
        if (id == null) {
            return null;
        }
        DigitalCertificate digitalCertificate = new DigitalCertificate();
        digitalCertificate.setId(id);
        return digitalCertificate;
    }
}
