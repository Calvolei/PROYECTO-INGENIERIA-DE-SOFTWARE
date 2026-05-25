package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperarioRequestDTO {
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private String identificacionNacional;
    private String codigoInterno;
}