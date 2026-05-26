package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.controller;

import com.modulopersonas.modulopersonas.application.service.ClienteUseCase;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.external.ClienteExternalResponse;
import com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Modulo3IntegrationController {

    private final ClienteUseCase clienteUseCase;

    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<ClienteExternalResponse> getClienteById(@PathVariable String idCliente) {
        Long id = Long.valueOf(idCliente);
        ClienteResponseDTO cliente = clienteUseCase.findById(id);

        ClienteExternalResponse response = new ClienteExternalResponse(
                String.valueOf(cliente.getId()),
                cliente.getIdentificacionNacional(),
                cliente.getNombreCompleto(),
                cliente.getNumeroCelular(),
                cliente.getDireccion()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clientes/nacional/{idNacional}")
    public ResponseEntity<ClienteExternalResponse> getClienteByIdNacional(@PathVariable String idNacional) {
        ClienteResponseDTO cliente = clienteUseCase.findByIdentificacionNacional(idNacional);

        ClienteExternalResponse response = new ClienteExternalResponse(
                String.valueOf(cliente.getId()),
                cliente.getIdentificacionNacional(),
                cliente.getNombreCompleto(),
                cliente.getNumeroCelular(),
                cliente.getDireccion()
        );
        return ResponseEntity.ok(response);
    }
}