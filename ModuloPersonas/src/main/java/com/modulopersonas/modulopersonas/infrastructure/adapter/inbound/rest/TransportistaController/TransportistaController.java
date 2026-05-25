package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.TransportistaController;

import com.modulopersonas.modulopersonas.application.service.TransportistaUseCase;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request.TransportistaRequestDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.TransportistaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transportistas")
@RequiredArgsConstructor
public class TransportistaController {

    private final TransportistaUseCase transportistaUseCase;

    @PostMapping
    public ResponseEntity<TransportistaResponseDTO> create(@Validated @RequestBody TransportistaRequestDTO requestDTO) {
        TransportistaResponseDTO response = transportistaUseCase.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportistaResponseDTO> findById(@PathVariable Long id) {
        TransportistaResponseDTO response = transportistaUseCase.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TransportistaResponseDTO>> findAll() {
        List<TransportistaResponseDTO> response = transportistaUseCase.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportistaResponseDTO> update(
            @PathVariable Long id,
            @Validated @RequestBody TransportistaRequestDTO requestDTO) {
        TransportistaResponseDTO response = transportistaUseCase.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transportistaUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activo")
    public ResponseEntity<TransportistaResponseDTO> toggleActivo(@PathVariable Long id) {
        TransportistaResponseDTO response = transportistaUseCase.toggleActivo(id);
        return ResponseEntity.ok(response);
    }
}