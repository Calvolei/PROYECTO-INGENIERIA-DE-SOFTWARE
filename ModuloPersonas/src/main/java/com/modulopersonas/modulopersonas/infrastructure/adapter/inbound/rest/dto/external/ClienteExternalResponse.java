package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.external;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteExternalResponse(
        @JsonProperty("id_cliente") String idCliente,
        @JsonProperty("id_nacional") String idNacional,
        @JsonProperty("nombre") String nombre,
        @JsonProperty("telefono") String telefono,
        @JsonProperty("direccion") String direccion
) {}