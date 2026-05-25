package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.OperarioController;


import com.modulopersonas.modulopersonas.application.port.OperarioUseCasePort;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.OperarioRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.OperarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operarios")
@RequiredArgsConstructor
public class OperarioController {

    private final OperarioUseCasePort operarioUseCase;

    // Crear un nuevo operario
    @PostMapping
    public ResponseEntity<OperarioResponseDTO> create(@Validated @RequestBody OperarioRequestDTO requestDTO) {
        OperarioResponseDTO response = operarioUseCase.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener operario por ID
    @GetMapping("/{id}")
    public ResponseEntity<OperarioResponseDTO> findById(@PathVariable Long id) {
        OperarioResponseDTO response = operarioUseCase.findById(id);
        return ResponseEntity.ok(response);
    }

    // Obtener todos los operarios
    @GetMapping
    public ResponseEntity<List<OperarioResponseDTO>> findAll() {
        List<OperarioResponseDTO> response = operarioUseCase.findAll();
        return ResponseEntity.ok(response);
    }

    // Actualizar operario por ID
    @PutMapping("/{id}")
    public ResponseEntity<OperarioResponseDTO> update(
            @PathVariable Long id,
            @Validated @RequestBody OperarioRequestDTO requestDTO) {
        OperarioResponseDTO response = operarioUseCase.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    // Eliminar operario por ID (borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        operarioUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por identificación nacional
    @GetMapping("/identificacion/{identificacionNacional}")
    public ResponseEntity<OperarioResponseDTO> findByIdentificacionNacional(@PathVariable String identificacionNacional) {
        OperarioResponseDTO response = operarioUseCase.findByIdentificacionNacional(identificacionNacional);
        return ResponseEntity.ok(response);
    }

    // Buscar por código interno
    @GetMapping("/codigo/{codigoInterno}")
    public ResponseEntity<OperarioResponseDTO> findByCodigoInterno(@PathVariable String codigoInterno) {
        OperarioResponseDTO response = operarioUseCase.findByCodigoInterno(codigoInterno);
        return ResponseEntity.ok(response);
    }

    // Buscar por número de celular
    @GetMapping("/celular/{numeroCelular}")
    public ResponseEntity<OperarioResponseDTO> findByNumeroCelular(@PathVariable String numeroCelular) {
        OperarioResponseDTO response = operarioUseCase.findByNumeroCelular(numeroCelular);
        return ResponseEntity.ok(response);
    }

    // Activar/desactivar operario (borrado lógico)
    @PatchMapping("/{id}/activo")
    public ResponseEntity<OperarioResponseDTO> toggleActivo(@PathVariable Long id) {
        OperarioResponseDTO response = operarioUseCase.toggleActivo(id);
        return ResponseEntity.ok(response);
    }
}
