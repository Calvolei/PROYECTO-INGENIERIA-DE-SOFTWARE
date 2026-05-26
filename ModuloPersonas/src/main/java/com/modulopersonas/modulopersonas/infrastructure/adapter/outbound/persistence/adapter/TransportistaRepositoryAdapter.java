package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.adapter;

import com.modulopersonas.modulopersonas.domain.enums.EstadoTransportista;
import com.modulopersonas.modulopersonas.domain.model.Transportista;
import com.modulopersonas.modulopersonas.domain.repository.TransportistaRepositoryPort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.repository.JpaTransportistaRepository;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.TransportistaEntity;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper.TransportistaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransportistaRepositoryAdapter implements TransportistaRepositoryPort {

    private final JpaTransportistaRepository jpaRepository;
    private final TransportistaEntityMapper entityMapper;

    @Override
    public Transportista save(Transportista domain) {
        TransportistaEntity entity = entityMapper.toEntity(domain);
        TransportistaEntity saved = jpaRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public Optional<Transportista> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Transportista> findAll() {
        return entityMapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Transportista> findByIdentificacionNacional(String identificacionNacional) {
        return jpaRepository.findByIdentificacionNacional(identificacionNacional)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Transportista> findByNumeroCelular(String numeroCelular) {
        return jpaRepository.findByNumeroCelular(numeroCelular)
                .map(entityMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByIdentificacionNacional(String identificacionNacional) {
        return jpaRepository.existsByIdentificacionNacional(identificacionNacional);
    }

    @Override
    public boolean existsByNumeroCelular(String numeroCelular) {
        return jpaRepository.existsByNumeroCelular(numeroCelular);
    }

    @Override
    public Optional<Transportista> findFirstByEstado(EstadoTransportista estado) {
        return jpaRepository.findFirstByEstado(estado)
                .map(entityMapper::toDomain);
    }
}