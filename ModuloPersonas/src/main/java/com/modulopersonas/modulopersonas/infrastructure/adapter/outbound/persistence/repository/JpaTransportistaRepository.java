package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.repository;

import com.modulopersonas.modulopersonas.domain.enums.EstadoTransportista;
import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.TransportistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaTransportistaRepository extends JpaRepository<TransportistaEntity, Long> {

    Optional<TransportistaEntity> findByIdentificacionNacional(String identificacionNacional);

    Optional<TransportistaEntity> findByNumeroCelular(String numeroCelular);

    boolean existsByIdentificacionNacional(String identificacionNacional);

    boolean existsByNumeroCelular(String numeroCelular);

    Optional<TransportistaEntity> findFirstByEstado(EstadoTransportista estado);
}