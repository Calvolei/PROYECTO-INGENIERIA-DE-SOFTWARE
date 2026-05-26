package com.modulopersonas.modulopersonas.application.service;

import com.modulopersonas.modulopersonas.application.port.OperarioUseCasePort;  // ← Importar interfaz
import com.modulopersonas.modulopersonas.domain.enums.RolOperario;
import com.modulopersonas.modulopersonas.domain.model.Operario;
import com.modulopersonas.modulopersonas.domain.repository.OperarioRepositoryPort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.OperarioRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.OperarioResponseDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.mapper.OperarioDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperarioUseCase implements OperarioUseCasePort {  // ← Implementar la interfaz

    private final OperarioRepositoryPort repositoryPort;
    private final OperarioDomainMapper domainMapper;

    @Override
    @Transactional
    public OperarioResponseDTO create(OperarioRequestDTO requestDTO) {
        // Agregar validaciones
        if (repositoryPort.existsByIdentificacionNacional(requestDTO.getIdentificacionNacional())) {
            throw new RuntimeException("Ya existe un operario con esa identificación");
        }
        if (repositoryPort.existsByCodigoInterno(requestDTO.getCodigoInterno())) {
            throw new RuntimeException("Ya existe un operario con ese código interno");
        }
        if (repositoryPort.existsByNumeroCelular(requestDTO.getNumeroCelular())) {
            throw new RuntimeException("Ya existe un operario con ese número de celular");
        }

        Operario domain = domainMapper.toDomain(requestDTO);
        Operario saved = repositoryPort.save(domain);
        return domainMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OperarioResponseDTO findById(Long id) {
        Operario domain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con ID: " + id));
        return domainMapper.toResponseDTO(domain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperarioResponseDTO> findAll() {
        return repositoryPort.findAll().stream()
                .map(domainMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public OperarioResponseDTO update(Long id, OperarioRequestDTO requestDTO) {
        Operario existingDomain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con ID: " + id));

        // Validaciones para actualización
        if (!existingDomain.getIdentificacionNacional().equals(requestDTO.getIdentificacionNacional()) &&
                repositoryPort.existsByIdentificacionNacional(requestDTO.getIdentificacionNacional())) {
            throw new RuntimeException("Ya existe otro operario con esa identificación");
        }
        if (!existingDomain.getCodigoInterno().equals(requestDTO.getCodigoInterno()) &&
                repositoryPort.existsByCodigoInterno(requestDTO.getCodigoInterno())) {
            throw new RuntimeException("Ya existe otro operario con ese código interno");
        }
        if (!existingDomain.getNumeroCelular().equals(requestDTO.getNumeroCelular()) &&
                repositoryPort.existsByNumeroCelular(requestDTO.getNumeroCelular())) {
            throw new RuntimeException("Ya existe otro operario con ese número de celular");
        }

        domainMapper.updateDomainFromRequest(requestDTO, existingDomain);
        Operario updated = repositoryPort.save(existingDomain);
        return domainMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repositoryPort.existsById(id)) {
            throw new RuntimeException("Operario no encontrado con ID: " + id);
        }
        repositoryPort.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public OperarioResponseDTO findByIdentificacionNacional(String identificacionNacional) {
        Operario domain = repositoryPort.findByIdentificacionNacional(identificacionNacional)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con identificación: " + identificacionNacional));
        return domainMapper.toResponseDTO(domain);
    }

    @Override
    @Transactional(readOnly = true)
    public OperarioResponseDTO findByCodigoInterno(String codigoInterno) {
        Operario domain = repositoryPort.findByCodigoInterno(codigoInterno)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con código interno: " + codigoInterno));
        return domainMapper.toResponseDTO(domain);
    }

    @Override
    @Transactional(readOnly = true)
    public OperarioResponseDTO findByNumeroCelular(String numeroCelular) {
        Operario domain = repositoryPort.findByNumeroCelular(numeroCelular)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con número de celular: " + numeroCelular));
        return domainMapper.toResponseDTO(domain);
    }

    @Override
    @Transactional
    public OperarioResponseDTO toggleActivo(Long id) {
        Operario domain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con ID: " + id));

        domain.setActivo(!domain.getActivo());
        Operario updated = repositoryPort.save(domain);
        return domainMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperarioResponseDTO> findByRol(RolOperario rolOperario) {
        List<Operario> operarios = repositoryPort.findByRol(rolOperario);
        return operarios.stream()
                .map(domainMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}