package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.request;

import com.modulopersonas.modulopersonas.domain.enums.RolOperario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperarioRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String apellido;

    @NotBlank
    @Size(min = 10, max = 10)
    private String numeroCelular;

    @NotBlank
    @Size(max = 12)
    private String identificacionNacional;

    @NotBlank
    @Size(max = 20)
    private String codigoInterno;

    @NotNull
    private RolOperario rol;  // ← NUEVO CAMPO
}