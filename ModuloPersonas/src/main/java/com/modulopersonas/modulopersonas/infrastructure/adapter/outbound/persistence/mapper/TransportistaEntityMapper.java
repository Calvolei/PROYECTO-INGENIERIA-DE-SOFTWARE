package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper;

import com.modulopersonas.modulopersonas.domain.model.Transportista;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.TransportistaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TransportistaEntityMapper {

    @Mapping(target = "nombreCompleto", ignore = true)
    Transportista toDomain(TransportistaEntity entity);

    @Mapping(target = "nombreCompleto", ignore = true)
    TransportistaEntity toEntity(Transportista domain);

    List<Transportista> toDomainList(List<TransportistaEntity> entities);

    List<TransportistaEntity> toEntityList(List<Transportista> domains);
}