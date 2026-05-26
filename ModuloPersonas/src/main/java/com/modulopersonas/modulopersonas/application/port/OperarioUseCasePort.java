package com.modulopersonas.modulopersonas.application.port;

import com.modulopersonas.modulopersonas.domain.enums.RolOperario;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.OperarioRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.OperarioResponseDTO;
import java.util.List;

public interface OperarioUseCasePort {
    OperarioResponseDTO create(OperarioRequestDTO requestDTO);
    OperarioResponseDTO findById(Long id);
    List<OperarioResponseDTO> findAll();
    OperarioResponseDTO update(Long id, OperarioRequestDTO requestDTO);
    void delete(Long id);
    OperarioResponseDTO findByIdentificacionNacional(String identificacionNacional);
    OperarioResponseDTO findByCodigoInterno(String codigoInterno);
    OperarioResponseDTO findByNumeroCelular(String numeroCelular);
    OperarioResponseDTO toggleActivo(Long id);
    List<OperarioResponseDTO> findByRol(RolOperario rolOperario);
}