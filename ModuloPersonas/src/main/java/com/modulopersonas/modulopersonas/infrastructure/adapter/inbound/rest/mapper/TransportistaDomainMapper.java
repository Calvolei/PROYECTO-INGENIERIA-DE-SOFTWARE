package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.mapper;


import com.modulopersonas.modulopersonas.domain.model.Transportista;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.TransportistaRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.TransportistaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportistaDomainMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    Transportista toDomain(TransportistaRequestDTO requestDTO);

    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
    TransportistaResponseDTO toResponseDTO(Transportista domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    void updateDomainFromRequest(TransportistaRequestDTO requestDTO, @MappingTarget Transportista domain);
}