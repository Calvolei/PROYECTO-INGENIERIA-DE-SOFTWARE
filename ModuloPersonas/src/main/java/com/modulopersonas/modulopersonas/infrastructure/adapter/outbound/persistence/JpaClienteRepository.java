package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence;


import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByIdentificacionNacional(String identificacionNacional);

    Optional<ClienteEntity> findByNumeroCelular(String numeroCelular);

    boolean existsByIdentificacionNacional(String identificacionNacional);

    boolean existsByNumeroCelular(String numeroCelular);
}