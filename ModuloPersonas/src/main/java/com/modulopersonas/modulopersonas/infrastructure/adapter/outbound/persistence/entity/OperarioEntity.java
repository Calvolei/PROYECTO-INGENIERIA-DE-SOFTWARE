package com.modulopersonas.modulopersonas.infrastructure.adapter.outbound.persistence.entity;

import com.modulopersonas.modulopersonas.domain.enums.RolOperario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operarios")
public class OperarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "numero_celular", nullable = false, unique = true, length = 10)
    private String numeroCelular;

    @Column(name = "identificacion_nacional", nullable = false, unique = true, length = 12)
    private String identificacionNacional;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "codigo_interno", nullable = false, unique = true, length = 20)
    private String codigoInterno;

    // ========== NUEVO CAMPO ROL ==========
    @Column(name = "rol", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RolOperario rol;

    // ========== MÉTODOS DE CICLO DE VIDA ==========

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (activo == null) {
            activo = true;
        }
    }

    // ========== MÉTODOS ÚTILES ==========

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}