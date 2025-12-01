package com.telconova.supportsuite.graphql.resolver;

import com.telconova.supportsuite.graphql.entity.Ticket;
import com.telconova.supportsuite.graphql.service.TicketGraphqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TicketQueryResolver {

    @Autowired

    private final TicketGraphqlService ticketGraphqlService;

    // Inyección por constructor (preferida sobre @Autowired en el campo)
    public TicketQueryResolver(TicketGraphqlService ticketGraphqlService) {
        this.ticketGraphqlService = ticketGraphqlService;
    }

    @QueryMapping
    public List<Ticket> obtenerTickets() {
        return ticketGraphqlService.obtenerTodosLosTickets();
    }

    @QueryMapping
    public Ticket obtenerTicketPorId(@Argument Long id) {
        Optional<Ticket> ticket = ticketGraphqlService.obtenerTicketPorId(id);
        return ticket.orElse(null);
    }

    @QueryMapping
    public List<Ticket> obtenerTicketsPorEstado(@Argument String estado) {
        return ticketGraphqlService.obtenerTicketsPorEstado(estado);
    }

    @QueryMapping
    public List<Ticket> obtenerTicketsPorCliente(@Argument Long clienteId) {
        return ticketGraphqlService.obtenerTicketsPorCliente(clienteId);
    }

    @QueryMapping
    public List<Map<String, Object>> contarTicketsPorEstado() {
        Map<String, Long> contadores = ticketGraphqlService.contarTicketsPorEstado();
        return contadores.entrySet().stream()
                .map(entry -> Map.of("estado", (Object)entry.getKey(), "total", (Object)entry.getValue()))
                .collect(java.util.stream.Collectors.toList());
    }
}