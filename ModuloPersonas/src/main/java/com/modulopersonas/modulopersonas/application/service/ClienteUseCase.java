package com.modulopersonas.modulopersonas.application.service;

import com.modulopersonas.modulopersonas.domain.model.Cliente;
import com.modulopersonas.modulopersonas.domain.repository.ClienteRepositoryPort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.ClienteRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.ClienteResponseDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.mapper.ClienteDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteRepositoryPort repositoryPort;
    private final ClienteDomainMapper domainMapper;

    @Transactional
    public ClienteResponseDTO create(ClienteRequestDTO requestDTO) {
        // Validaciones
        if (repositoryPort.existsByIdentificacionNacional(requestDTO.getIdentificacionNacional())) {
            throw new RuntimeException("Ya existe un cliente con esa identificación nacional");
        }
        if (repositoryPort.existsByNumeroCelular(requestDTO.getNumeroCelular())) {
            throw new RuntimeException("Ya existe un cliente con ese número de celular");
        }

        Cliente domain = domainMapper.toDomain(requestDTO);
        Cliente saved = repositoryPort.save(domain);
        return domainMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {
        Cliente domain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        return domainMapper.toResponseDTO(domain);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findAll() {
        return repositoryPort.findAll().stream()
                .map(domainMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public ClienteResponseDTO update(Long id, ClienteRequestDTO requestDTO) {
        Cliente existingDomain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // Validaciones para actualización
        if (!existingDomain.getIdentificacionNacional().equals(requestDTO.getIdentificacionNacional()) &&
                repositoryPort.existsByIdentificacionNacional(requestDTO.getIdentificacionNacional())) {
            throw new RuntimeException("Ya existe otro cliente con esa identificación nacional");
        }
        if (!existingDomain.getNumeroCelular().equals(requestDTO.getNumeroCelular()) &&
                repositoryPort.existsByNumeroCelular(requestDTO.getNumeroCelular())) {
            throw new RuntimeException("Ya existe otro cliente con ese número de celular");
        }

        domainMapper.updateDomainFromRequest(requestDTO, existingDomain);
        Cliente updated = repositoryPort.save(existingDomain);
        return domainMapper.toResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repositoryPort.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        repositoryPort.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO findByIdentificacionNacional(String identificacionNacional) {
        Cliente domain = repositoryPort.findByIdentificacionNacional(identificacionNacional)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con identificación: " + identificacionNacional));
        return domainMapper.toResponseDTO(domain);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO findByNumeroCelular(String numeroCelular) {
        Cliente domain = repositoryPort.findByNumeroCelular(numeroCelular)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con número de celular: " + numeroCelular));
        return domainMapper.toResponseDTO(domain);
    }

    @Transactional
    public ClienteResponseDTO toggleActivo(Long id) {
        Cliente domain = repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        domain.setActivo(!domain.getActivo());
        Cliente updated = repositoryPort.save(domain);
        return domainMapper.toResponseDTO(updated);
    }
}