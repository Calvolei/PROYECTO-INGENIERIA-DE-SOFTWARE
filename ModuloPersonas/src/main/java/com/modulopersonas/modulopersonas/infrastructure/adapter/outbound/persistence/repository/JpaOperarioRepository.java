package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.repository;


import com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity.OperarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaOperarioRepository extends JpaRepository<OperarioEntity, Long> {

    // Buscar por identificación nacional (única)
    Optional<OperarioEntity> findByIdentificacionNacional(String identificacionNacional);

    // Buscar por código interno (único)
    Optional<OperarioEntity> findByCodigoInterno(String codigoInterno);

    // Buscar por número de celular (único)
    Optional<OperarioEntity> findByNumeroCelular(String numeroCelular);

    // Verificar si existe por identificación nacional
    boolean existsByIdentificacionNacional(String identificacionNacional);

    // Verificar si existe por código interno
    boolean existsByCodigoInterno(String codigoInterno);

    // Verificar si existe por número de celular
    boolean existsByNumeroCelular(String numeroCelular);
}