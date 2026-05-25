package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper;

import com.modulopersonas.modulopersonas.domain.model.Cliente;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    // Entity → Domain
    @Mapping(target = "fechaCreacion", source = "fechaRegistro")
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "nombreCompleto", ignore = true)
    Cliente toDomain(ClienteEntity entity);

    // Domain → Entity
    @Mapping(target = "fechaRegistro", source = "fechaCreacion")
    @Mapping(target = "nombreCompleto", ignore = true)
    @Mapping(target = "onCreate", ignore = true)
    ClienteEntity toEntity(Cliente domain);

    // Lista Entity → Lista Domain
    List<Cliente> toDomainList(List<ClienteEntity> entities);

    // Lista Domain → Lista Entity
    List<ClienteEntity> toEntityList(List<Cliente> domains);
}