package com.modulopersonas.modulopersonas.infrastructure.adapter.inbound.rest.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public record TransportistaDisponibleResponseDTO(
        @JsonProperty("idTransportista") Long idTransportista,
        @JsonProperty("estado") String estado
) {}