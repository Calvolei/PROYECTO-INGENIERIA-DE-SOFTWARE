package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper;


import com.modulopersonas.modulopersonas.domain.model.Operario;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.OperarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OperarioEntityMapper {

    // Entity → Domain
    @Mapping(target = "nombreCompleto", ignore = true)
    Operario toDomain(OperarioEntity entity);

    // Domain → Entity
    @Mapping(target = "nombreCompleto", ignore = true)
    @Mapping(target = "onCreate", ignore = true)
    OperarioEntity toEntity(Operario domain);

    // Lista Entity → Lista Domain
    List<Operario> toDomainList(List<OperarioEntity> entities);

    // Lista Domain → Lista Entity
    List<OperarioEntity> toEntityList(List<Operario> domains);
}