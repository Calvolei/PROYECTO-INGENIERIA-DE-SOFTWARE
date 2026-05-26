package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper;

import com.modulopersonas.modulopersonas.domain.model.Cliente;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClienteEntityMapper {

    // Entity → Domain
    @Mapping(target = "fechaCreacion", source = "fechaRegistro")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(source = "email", target = "email")  // ← AGREGAR
    Cliente toDomain(ClienteEntity entity);

    // Domain → Entity
    @Mapping(target = "fechaRegistro", source = "fechaCreacion")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(source = "email", target = "email")  // ← AGREGAR
    ClienteEntity toEntity(Cliente domain);

    // Lista Entity → Lista Domain
    List<Cliente> toDomainList(List<ClienteEntity> entities);

    // Lista Domain → Lista Entity
    List<ClienteEntity> toEntityList(List<Cliente> domains);
}