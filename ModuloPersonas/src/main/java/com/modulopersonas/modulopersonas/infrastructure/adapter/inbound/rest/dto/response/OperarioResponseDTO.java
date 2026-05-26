package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response;

import com.modulopersonas.modulopersonas.domain.enums.RolOperario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperarioResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String numeroCelular;
    private String identificacionNacional;
    private String codigoInterno;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
    private RolOperario rol;  // ← NUEVO CAMPO
}