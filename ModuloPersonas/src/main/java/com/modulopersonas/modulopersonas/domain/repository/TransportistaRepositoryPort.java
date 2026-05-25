package com.modulopersonas.modulopersonas.domain.repository;

import com.modulopersonas.modulopersonas.domain.model.Transportista;
import java.util.List;
import java.util.Optional;

public interface TransportistaRepositoryPort {

    Transportista save(Transportista transportista);

    Optional<Transportista> findById(Long id);

    List<Transportista> findAll();

    void deleteById(Long id);

    Optional<Transportista> findByIdentificacionNacional(String identificacionNacional);

    Optional<Transportista> findByNumeroCelular(String numeroCelular);

    boolean existsById(Long id);

    boolean existsByIdentificacionNacional(String identificacionNacional);

    boolean existsByNumeroCelular(String numeroCelular);
}
