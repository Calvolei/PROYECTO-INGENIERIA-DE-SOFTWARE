package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String numeroCelular;
    private String metodoPago;
    private String direccion;
    private String identificacionNacional;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
}