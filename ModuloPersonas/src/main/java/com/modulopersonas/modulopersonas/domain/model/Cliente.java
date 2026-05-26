package com.modulopersonas.modulopersonas.domain.model;

import com.modulopersonas.modulopersonas.domain.enums.MetodoPago;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {

    // Atributos
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private MetodoPago metodoPago;
    private String direccion;
    private String identificacionNacional;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String email;

    // Patrones para validaciones
    private static final Pattern PATRON_CELULAR = Pattern.compile("^3\\d{9}$");
    private static final Pattern PATRON_IDENTIFICACION = Pattern.compile("^\\d{6,12}$");
    private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]{2,50}$");

    // Constructor vacío
    public Cliente() {
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Constructor con campos obligatorios
    public Cliente(String nombre, String apellido, String numeroCelular,
                   MetodoPago metodoPago, String direccion, String identificacionNacional) {
        this();
        setNombre(nombre);
        setApellido(apellido);
        setNumeroCelular(numeroCelular);
        setMetodoPago(metodoPago);
        setDireccion(direccion);
        setIdentificacionNacional(identificacionNacional);
    }

    // ========== GETTERS Y SETTERS CON VALIDACIONES ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (!PATRON_NOMBRE.matcher(nombre).matches()) {
            throw new IllegalArgumentException("El nombre solo debe contener letras y espacios (2-50 caracteres)");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (!PATRON_NOMBRE.matcher(apellido).matches()) {
            throw new IllegalArgumentException("El apellido solo debe contener letras y espacios (2-50 caracteres)");
        }
        this.apellido = apellido.trim();
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        if (numeroCelular == null || numeroCelular.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de celular no puede estar vacío");
        }
        String limpio = numeroCelular.trim().replaceAll("\\s", "").replace("+57", "");
        if (!PATRON_CELULAR.matcher(limpio).matches()) {
            throw new IllegalArgumentException("Número de celular inválido. Debe ser 10 dígitos empezando con 3 (ej: 3123456789)");
        }
        this.numeroCelular = limpio;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo");
        }
        this.metodoPago = metodoPago;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        if (direccion.length() < 5 || direccion.length() > 200) {
            throw new IllegalArgumentException("La dirección debe tener entre 5 y 200 caracteres");
        }
        this.direccion = direccion.trim();
    }

    public String getIdentificacionNacional() {
        return identificacionNacional;
    }

    public void setIdentificacionNacional(String identificacionNacional) {
        if (identificacionNacional == null || identificacionNacional.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación nacional no puede estar vacía");
        }
        String limpio = identificacionNacional.trim().replaceAll("\\s", "");
        if (!PATRON_IDENTIFICACION.matcher(limpio).matches()) {
            throw new IllegalArgumentException("Identificación inválida. Debe tener entre 6 y 12 dígitos numéricos");
        }
        this.identificacionNacional = limpio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo != null ? activo : true;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : LocalDateTime.now();
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }


    // Metodo para actualizar automáticamente la fecha de modificación
    public void actualizar() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Metodo para obtener nombre completo
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroCelular='" + numeroCelular + '\'' +
                ", metodoPago=" + metodoPago +
                ", direccion='" + direccion + '\'' +
                ", identificacionNacional='" + identificacionNacional + '\'' +
                ", activo=" + activo +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
