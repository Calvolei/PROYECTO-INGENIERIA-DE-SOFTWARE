package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.controller;


import com.modulopersonas.modulopersonas.application.service.TransportistaUseCase;
import com.modulopersonas.modulopersonas.domain.enums.EstadoTransportista;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.TransportistaDisponibleResponseDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.TransportistaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transportistas")
@RequiredArgsConstructor
public class Modulo2IntegrationController {

    private final TransportistaUseCase transportistaUseCase;

    /**
     * Endpoint para obtener un transportista disponible
     * GET /transportistas/disponible
     */
    @GetMapping("/disponible")
    public ResponseEntity<TransportistaDisponibleResponseDTO> getAvailableTransporter() {
        TransportistaResponseDTO transportista = transportistaUseCase.findFirstByEstado(EstadoTransportista.DISPONIBLE);

        TransportistaDisponibleResponseDTO response = new TransportistaDisponibleResponseDTO(
                transportista.getId(),
                transportista.getEstado().name()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para validar que un transportista existe
     * GET /transportistas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Void> validateExistence(@PathVariable Long id) {
        transportistaUseCase.findById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para cambiar el estado de un transportista
     * PATCH /transportistas/{id}/estado
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<TransportistaResponseDTO> updateEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        EstadoTransportista nuevoEstado = EstadoTransportista.valueOf(estado.toUpperCase());
        TransportistaResponseDTO updated = transportistaUseCase.updateEstado(id, nuevoEstado);
        return ResponseEntity.ok(updated);
    }
}