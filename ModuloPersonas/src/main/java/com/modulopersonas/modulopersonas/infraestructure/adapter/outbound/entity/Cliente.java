package com.modulopersonas.modulopersonas.infraestructure.adapter.outbound.entity;

import main.java.com.modulopersonas.modulopersonas.domain.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "numero_celular", nullable = false, unique = true, length = 10)
    private String numeroCelular;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 20)
    private MetodoPago metodoPago;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "id_nacional", nullable = false, unique = true, length = 12)
    private String identificacionNacional;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "puntos_fidelidad")
    private Integer puntosFidelidad = 0;

    // ========== MÉTODOS DE CICLO DE VIDA (Lombok NO los genera) ==========

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (activo == null) {
            activo = true;
        }
        if (puntosFidelidad == null) {
            puntosFidelidad = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }


    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}