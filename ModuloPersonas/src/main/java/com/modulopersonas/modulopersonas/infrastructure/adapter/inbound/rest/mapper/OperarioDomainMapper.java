package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.mapper;

import com.modulopersonas.modulopersonas.domain.model.Operario;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.OperarioRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.OperarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OperarioDomainMapper {

    // Request to Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    Operario toDomain(OperarioRequestDTO requestDTO);

    // Domain to Response
    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
    OperarioResponseDTO toResponseDTO(Operario domain);

    // Update Domain from Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    void updateDomainFromRequest(OperarioRequestDTO requestDTO, @MappingTarget Operario domain);
}