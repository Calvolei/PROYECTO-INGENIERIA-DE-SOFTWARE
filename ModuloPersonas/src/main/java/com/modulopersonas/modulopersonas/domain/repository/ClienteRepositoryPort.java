package com.modulopersonas.modulopersonas.domain.repository;


import com.modulopersonas.modulopersonas.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    void deleteById(Long id);

    Optional<Cliente> findByIdentificacionNacional(String identificacionNacional);

    Optional<Cliente> findByNumeroCelular(String numeroCelular);

    boolean existsById(Long id);

    boolean existsByIdentificacionNacional(String identificacionNacional);

    boolean existsByNumeroCelular(String numeroCelular);
}