package com.modulopersonas.modulopersonas.domain.repository;

import com.modulopersonas.modulopersonas.domain.model.Operario;
import java.util.List;
import java.util.Optional;

public interface OperarioRepositoryPort {

    Operario save(Operario operario);

    Optional<Operario> findById(Long id);

    List<Operario> findAll();

    void deleteById(Long id);

    Optional<Operario> findByIdentificacionNacional(String identificacionNacional);

    Optional<Operario> findByCodigoInterno(String codigoInterno);

    Optional<Operario> findByNumeroCelular(String numeroCelular);

    boolean existsByIdentificacionNacional(String identificacionNacional);

    boolean existsByCodigoInterno(String codigoInterno);

    boolean existsByNumeroCelular(String numeroCelular);

    boolean existsById(Long Id);
}
