# Implementation Plan: Módulo Personas - Core de Identidades

**Spec:** especificaciones/modulo_personas.md

## Arquitectura
Siguiendo los lineamientos de Arquitectura Limpia, el módulo se divide en:
* **Domain:** Entidades `Operario`, `Transportista`, `Cliente`.
* **Application:** Casos de uso de gestión y consulta.
* **Infrastructure:** Adaptadores REST para integración con M1, M2 y M3.

## Project Structure

src/main/java/com/distribuidora/peopleapi/
├── domain/
│   ├── model/
│   │   ├── Operario.java
│   │   ├── Transportista.java
│   │   └── Cliente.java
├── application/
│   ├── service/
│   │   ├── GestionOperarioUseCase.java
│   │   ├── GestionTransportistaUseCase.java
│   │   └── GestionClienteUseCase.java
└── infrastructure/
├── adapter/inbound/rest/
│   ├── OperarioController.java      # Endpoint para M1
│   ├── TransportistaController.java # Endpoint para M2
│   └── ClienteController.java       # Endpoint para M3

## Implementation Phases

### Phase 1: Gestión de Entidades (Domain Layer)
    [ ] T001 Crear modelo `Operario` (ID, nombre, área, estado).
    [ ] T002 Crear modelo `Transportista` (ID, nombre, teléfono, capacidad).
    [ ] T003 Crear modelo `Cliente` (NIT/Cédula, nombre, dirección, ciudad).

### Phase 2: Casos de Uso (Application Layer)
    [ ] T004 Implementar lógica de validación (campos obligatorios y no duplicidad)[cite: 4].
    [ ] T005 Crear servicios de consulta para uso externo (M1, M2, M3).

### Phase 3: Exposición de Servicios (Infrastructure Layer)
    [ ] T006 Crear controladores REST para M1 (operarios).
    [ ] T007 Crear controladores REST para M2 (asignación de rutas/transportistas).
    [ ] T008 Crear controladores REST para M3 (perfiles financieros/cobros).

## Success Criteria Técnicos
* **SC-001:** El sistema debe confirmar el éxito de la acción tras validar obligatoriedad de campos.
* **SC-002:** API de consulta para M1, M2 y M3 debe responder con la estructura de datos definida en el documento.
