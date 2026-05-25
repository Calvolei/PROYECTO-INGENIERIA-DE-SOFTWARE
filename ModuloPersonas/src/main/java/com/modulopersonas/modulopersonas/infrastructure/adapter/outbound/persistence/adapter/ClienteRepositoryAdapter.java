package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.adapter;


import com.modulopersonas.modulopersonas.domain.model.Cliente;
import com.modulopersonas.modulopersonas.domain.repository.ClienteRepositoryPort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.repository.JpaClienteRepository;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.ClienteEntity;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.mapper.ClienteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final JpaClienteRepository jpaRepository;
    private final ClienteEntityMapper entityMapper;

    @Override
    public Cliente save(Cliente domain) {
        ClienteEntity entity = entityMapper.toEntity(domain);
        ClienteEntity saved = jpaRepository.save(entity);
        return entityMapper.toDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return entityMapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> findByIdentificacionNacional(String identificacionNacional) {
        return jpaRepository.findByIdentificacionNacional(identificacionNacional)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByNumeroCelular(String numeroCelular) {
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
}