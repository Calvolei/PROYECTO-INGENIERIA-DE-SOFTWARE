package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.adapter;

import com.modulopersonas.modulopersonas.domain.model.Operario;
import com.modulopersonas.modulopersonas.domain.repository.OperarioRepositoryPort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.repository.JpaOperarioRepository;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.OperarioEntity;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper.OperarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OperarioRepositoryAdapter implements OperarioRepositoryPort {

    private final JpaOperarioRepository jpaRepository;
    private final OperarioEntityMapper entityMapper;

    @Override
    public Operario save(Operario domain) {
        OperarioEntity entity = entityMapper.toEntity(domain);
        OperarioEntity saved = jpaRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public Optional<Operario> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Operario> findAll() {
        return entityMapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Operario> findByIdentificacionNacional(String identificacionNacional) {
        return jpaRepository.findByIdentificacionNacional(identificacionNacional)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Operario> findByCodigoInterno(String codigoInterno) {
        return jpaRepository.findByCodigoInterno(codigoInterno)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Operario> findByNumeroCelular(String numeroCelular) {
        return jpaRepository.findByNumeroCelular(numeroCelular)
                .map(entityMapper::toDomain);
    }

    @Override
    public boolean existsByIdentificacionNacional(String identificacionNacional) {
        return jpaRepository.existsByIdentificacionNacional(identificacionNacional);
    }

    @Override
    public boolean existsByCodigoInterno(String codigoInterno) {
        return jpaRepository.existsByCodigoInterno(codigoInterno);
    }

    @Override
    public boolean existsByNumeroCelular(String numeroCelular) {
        return jpaRepository.existsByNumeroCelular(numeroCelular);
    }

    @Override
    public boolean existsById(Long Id) {
        return false;
    }
}