package com.telconova.supportsuite.graphql.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) utilizado para la entrada de datos (Input)
 * en las mutaciones de GraphQL para crear o actualizar un Ticket.
 */
@Data
public class TicketInput {

    private String titulo;
    private String descripcion;

    // Se mantiene como String para que el servicio pueda validar y convertirlo al enum
    private String prioridad;

    private Long clienteId;
    private String asignadoA;
}
