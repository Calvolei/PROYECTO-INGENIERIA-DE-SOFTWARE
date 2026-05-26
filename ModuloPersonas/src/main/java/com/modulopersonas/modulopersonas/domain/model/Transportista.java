package com.modulopersonas.modulopersonas.domain.model;

import com.modulopersonas.modulopersonas.domain.enums.EstadoTransportista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private String identificacionNacional;
    private Boolean activo = true;
    private LocalDateTime fechaRegistro;

    // ========== NUEVO CAMPO ==========
    private EstadoTransportista estado = EstadoTransportista.DISPONIBLE;


    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}