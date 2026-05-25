package com.modulopersonas.modulopersonas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operario {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private String identificacionNacional;
    private Boolean activo = true;
    private LocalDateTime fechaRegistro;

    // Campos específicos para Operario (todos son iguales)
    private String codigoInterno;              // Código interno del operario (ej: OP-001)

    // Métodos útiles
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

}
