package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.mapper;

import com.modulopersonas.modulopersonas.domain.model.Cliente;
import com.modulopersonas.modulopersonas.domain.enums.MetodoPago;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.ClienteRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.ClienteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClienteDomainMapper {

    // RequestDTO → Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(source = "metodoPago", target = "metodoPago", qualifiedByName = "stringToMetodoPago")
    Cliente toDomain(ClienteRequestDTO requestDTO);

    // Domain → ResponseDTO
    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
    @Mapping(source = "fechaCreacion", target = "fechaRegistro")
    @Mapping(source = "metodoPago", target = "metodoPago", qualifiedByName = "metodoPagoToString")
    ClienteResponseDTO toResponseDTO(Cliente domain);

    // Update Domain from Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(source = "metodoPago", target = "metodoPago", qualifiedByName = "stringToMetodoPago")
    void updateDomainFromRequest(ClienteRequestDTO requestDTO, @MappingTarget Cliente domain);

    // Conversiones manuales
    @Named("stringToMetodoPago")
    default MetodoPago stringToMetodoPago(String metodoPago) {
        if (metodoPago == null) {
            return null;
        }
        try {
            return MetodoPago.valueOf(metodoPago.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Método de pago inválido: " + metodoPago + ". Valores permitidos: " +
                    java.util.Arrays.toString(MetodoPago.values()));
        }
    }

    @Named("metodoPagoToString")
    default String metodoPagoToString(MetodoPago metodoPago) {
        return metodoPago != null ? metodoPago.name() : null;
    }
}