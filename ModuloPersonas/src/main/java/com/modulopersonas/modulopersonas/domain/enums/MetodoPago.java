package main.java.com.modulopersonas.modulopersonas.domain.enums;

public enum MetodoPago {
    CARTERA_COMERCIAL("CARTERA COMERCIAL"),
    EFECTIVO("Efectivo");

    private final String descripcion;

    MetodoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static MetodoPago fromDescripcion(String descripcion) {
        for (MetodoPago metodo : values()) {
            if (metodo.descripcion.equalsIgnoreCase(descripcion)) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Método de pago no válido: " + descripcion);
    }
}
