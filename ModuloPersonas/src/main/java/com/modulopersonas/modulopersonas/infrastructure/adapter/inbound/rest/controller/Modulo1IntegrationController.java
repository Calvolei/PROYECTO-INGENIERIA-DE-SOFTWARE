package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.controller;


import com.modulopersonas.modulopersonas.application.port.OperarioUseCasePort;
import com.modulopersonas.modulopersonas.application.service.ClienteUseCase;
import com.modulopersonas.modulopersonas.domain.enums.RolOperario;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.ClienteResponseDTO;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.OperarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class Modulo1IntegrationController {

    private final OperarioUseCasePort operarioUseCase;
    private final ClienteUseCase clienteUseCase;

    /**
     * Endpoint para buscar operario por cédula (identificación nacional)
     * GET /api/usuarios/operarios/{cedula}
     */
    @GetMapping("/operarios/{cedula}")
    public ResponseEntity<Map<String, Object>> findByCedula(@PathVariable String cedula) {
        OperarioResponseDTO operario = operarioUseCase.findByIdentificacionNacional(cedula);

        Map<String, Object> response = new HashMap<>();
        response.put("id", operario.getId());
        response.put("nombre", operario.getNombreCompleto());
        response.put("cedula", operario.getIdentificacionNacional());
        response.put("activo", operario.getActivo());
        response.put("rol", operario.getRol().name());  // Enum a String

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para buscar operario por ID interno
     * GET /api/usuarios/operarios/id/{id}
     */
    @GetMapping("/operarios/id/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        OperarioResponseDTO operario = operarioUseCase.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("id", operario.getId());
        response.put("nombre", operario.getNombreCompleto());
        response.put("cedula", operario.getIdentificacionNacional());
        response.put("activo", operario.getActivo());
        response.put("rol", operario.getRol().name());

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para listar operarios por rol
     * GET /api/usuarios/operarios?rol={rol}
     */
    @GetMapping("/operarios")
    public ResponseEntity<List<Map<String, Object>>> findByRol(@RequestParam String rol) {
        // Convertir String a enum (validar que sea válido)
        RolOperario rolEnum;
        try {
            rolEnum = RolOperario.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        List<OperarioResponseDTO> operarios = operarioUseCase.findByRol(rolEnum);

        List<Map<String, Object>> response = operarios.stream()
                .map(operario -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", operario.getId());
                    map.put("nombre", operario.getNombreCompleto());
                    map.put("cedula", operario.getIdentificacionNacional());
                    map.put("activo", operario.getActivo());
                    map.put("rol", operario.getRol().name());
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para buscar cliente por cédula (identificación nacional)
     * GET /api/usuarios/clientes/{cedula}
     */
    @GetMapping("/clientes/{cedula}")
    public ResponseEntity<Map<String, Object>> findClienteByCedula(@PathVariable String cedula) {
        ClienteResponseDTO cliente = clienteUseCase.findByIdentificacionNacional(cedula);

        Map<String, Object> response = new HashMap<>();
        response.put("cedula", cliente.getIdentificacionNacional());
        response.put("nombre", cliente.getNombreCompleto());
        response.put("telefono", cliente.getNumeroCelular());
        response.put("email", null);  // Tu tabla no tiene email, devuelve null
        response.put("direccion", cliente.getDireccion());
        response.put("activo", cliente.getActivo());

        return ResponseEntity.ok(response);
    }
}