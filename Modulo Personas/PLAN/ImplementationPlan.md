# Implementation Plan: Módulo Personas - Core de Identidades

**Spec:** especificaciones/modulo_personas.md

## Arquitectura
Siguiendo los lineamientos de Arquitectura Limpia, el módulo se divide en:
* **Domain:** Entidades `Operario`, `Transportista`, `Cliente`.
* **Application:** Casos de uso de gestión y consulta.
* **Infrastructure:** Adaptadores REST para integración con M1, M2 y M3.

### Project Structure
``` text
src/main/java/com/distribuidora/peopleapi/
├── domain/
│   ├── model/                  # Entidades de dominio (POJOs puros)
│   ├── repository/             # Interfaces de salida (Puertos de salida)
│   └── exception/              # Excepciones específicas del dominio
├── application/
│   ├── service/                # Implementación de casos de uso
|   |   ├── GestionOperarioUseCase.java
│   │   ├── GestionTransportistaUseCase.java
│   │   └── GestionClienteUseCase.java                       
│   └── port/                   # Interfaces de entrada (Puertos de entrada)
└── infrastructure/
    ├── adapter/
    │   ├── inbound/            # Entrada: Controladores y DTOs
    │   │   ├── rest/
    |   |   |   ├──OperarioController.java       # Endpoint para M1
    |   |   |   ├── TransportistaController.java  # Endpoint para M2
    |   |   |   └── ClienteController.java        # Endpoint para M3
    │   │   │   ├── dto/        # Objetos de transferencia de datos (Request/Response)
    │   │   │   ├── mapper/     # Conversión DTO <-> Domain
    │   │   │   └── exception/  # Manejadores globales de errores (@ControllerAdvice)
    │   └── outbound/           # Salida: Adaptadores para BD (JPA, etc.)
    │       └── persistence/
    │           ├── entity/     # Entidades de base de datos (@Entity)
    │           └── mapper/     # Conversión Entity <-> Domain
```
 
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
