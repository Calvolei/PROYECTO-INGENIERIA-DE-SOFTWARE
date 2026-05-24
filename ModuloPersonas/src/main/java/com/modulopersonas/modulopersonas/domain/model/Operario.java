package com.modulopersonas.modulopersonas.domain.model;

import main.java.com.modulopersonas.modulopersonas.domain.enums.MetodoPago;
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
    private MetodoPago metodoPago;
    private String direccion;
    private String identificacionNacional;
    private Boolean activo = true;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    // Campos específicos para Operario (todos son iguales)
    private String codigoInterno;              // Código interno del operario (ej: OP-001)
    private Boolean disponible = true;         // Si está disponible para trabajar
    private String zonaAsignada;               // Área de trabajo (Ej: "Zona A", "Empaque 1", etc.)
    private Integer pedidosProcesadosHoy = 0;  // Contador de pedidos procesados hoy
    private Double eficienciaPromedio = 0.0;   // Porcentaje de eficiencia (0-100)

    // Métodos útiles
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public void incrementarPedidosProcesados() {
        if (this.pedidosProcesadosHoy == null) {
            this.pedidosProcesadosHoy = 0;
        }
        this.pedidosProcesadosHoy++;
    }
}
