package com.modulopersonas.modulopersonas.application.service;

import com.modulopersonas.modulopersonas.domain.model.Transportista;
import com.modulopersonas.modulopersonas.domain.repository.TransportistaRepositoryPort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.TransportistaRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.TransportistaResponseDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.mapper.TransportistaDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportistaUseCase {

    private final TransportistaRepositoryPort repositoryPort;
    private final TransportistaDomainMapper domainMapper;

    @Transactional
    public TransportistaResponseDTO create(TransportistaRequestDTO requestDTO) {
        if (repositoryPort.existsByIdentificacionNacional(requestDTO.getIdentificacionNacional())) {
            throw new RuntimeException("Ya existe un transportista con esa identificación");
        }
        if (repositoryPort.existsByNumeroCelular(requestDTO.getNumeroCelular())) {
            throw new RuntimeException("Ya existe un transportista con ese número de celular");
        }

        Transportista domain = domainMapper.toDomain(requestDTO);
        Transportista saved = repositoryPort.save(domain);
        return domainMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public TransportistaResponseDTO findById(Long id) {
        Transportista domain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));
        return domainMapper.toResponseDTO(domain);
    }

    @Transactional(readOnly = true)
    public List<TransportistaResponseDTO> findAll() {
        return repositoryPort.findAll().stream()
                .map(domainMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public TransportistaResponseDTO update(Long id, TransportistaRequestDTO requestDTO) {
        Transportista existing = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

        if (!existing.getIdentificacionNacional().equals(requestDTO.getIdentificacionNacional()) &&
                repositoryPort.existsByIdentificacionNacional(requestDTO.getIdentificacionNacional())) {
            throw new RuntimeException("Ya existe otro transportista con esa identificación");
        }
        if (!existing.getNumeroCelular().equals(requestDTO.getNumeroCelular()) &&
                repositoryPort.existsByNumeroCelular(requestDTO.getNumeroCelular())) {
            throw new RuntimeException("Ya existe otro transportista con ese número de celular");
        }

        domainMapper.updateDomainFromRequest(requestDTO, existing);
        Transportista updated = repositoryPort.save(existing);
        return domainMapper.toResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repositoryPort.existsById(id)) {
            throw new RuntimeException("Transportista no encontrado");
        }
        repositoryPort.deleteById(id);
    }

    @Transactional
    public TransportistaResponseDTO toggleActivo(Long id) {
        Transportista domain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));

        domain.setActivo(!domain.getActivo());
        Transportista updated = repositoryPort.save(domain);
        return domainMapper.toResponseDTO(updated);
    }
}