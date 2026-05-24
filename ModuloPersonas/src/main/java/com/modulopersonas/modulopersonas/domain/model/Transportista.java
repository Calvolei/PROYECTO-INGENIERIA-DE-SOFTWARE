package com.modulopersonas.modulopersonas.domain.model;

import main.java.com.modulopersonas.modulopersonas.domain.enums.MetodoPago;
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
    private MetodoPago metodoPago;
    private String direccion;
    private String identificacionNacional;
    private Boolean activo = true;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    // Campos específicos para Transportista
    private String numeroLicencia;
    private String tipoVehiculo;        // ej: "Moto", "Camión", "Furgoneta"
    private String placaVehiculo;
    private Boolean disponible;          // Si está disponible para asignar entregas
    private Double calificacionPromedio; // 1.0 a 5.0

    // Método útil
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
