package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.ClienteController;

import com.modulopersonas.modulopersonas.application.service.ClienteUseCase;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.ClienteRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@Validated @RequestBody ClienteRequestDTO requestDTO) {
        ClienteResponseDTO response = clienteUseCase.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
        ClienteResponseDTO response = clienteUseCase.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll() {
        List<ClienteResponseDTO> response = clienteUseCase.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(
            @PathVariable Long id,
            @Validated @RequestBody ClienteRequestDTO requestDTO) {
        ClienteResponseDTO response = clienteUseCase.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/identificacion/{identificacionNacional}")
    public ResponseEntity<ClienteResponseDTO> findByIdentificacionNacional(@PathVariable String identificacionNacional) {
        ClienteResponseDTO response = clienteUseCase.findByIdentificacionNacional(identificacionNacional);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/celular/{numeroCelular}")
    public ResponseEntity<ClienteResponseDTO> findByNumeroCelular(@PathVariable String numeroCelular) {
        ClienteResponseDTO response = clienteUseCase.findByNumeroCelular(numeroCelular);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<ClienteResponseDTO> toggleActivo(@PathVariable Long id) {
        ClienteResponseDTO response = clienteUseCase.toggleActivo(id);
        return ResponseEntity.ok(response);
    }
}