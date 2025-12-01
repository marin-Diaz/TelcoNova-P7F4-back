package com.telconova.supportsuite.graphql.service;

import com.telconova.supportsuite.graphql.entity.Ticket;
import com.telconova.supportsuite.graphql.repository.TicketGraphqlRepository;
import com.telconova.supportsuite.graphql.repository.ComentarioRepository;
import com.telconova.supportsuite.graphql.dto.TicketInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketGraphqlService {

    @Autowired
    private final TicketGraphqlRepository ticketRepository;
    private final ComentarioRepository comentarioRepository;

    public TicketGraphqlService(TicketGraphqlRepository ticketRepository, ComentarioRepository comentarioRepository) {
        this.ticketRepository = ticketRepository;
        this.comentarioRepository = comentarioRepository;
    }

    // Queries
    public List<Ticket> obtenerTodosLosTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> obtenerTicketPorId(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> obtenerTicketsPorEstado(String estado) {
        try {
            return ticketRepository.findByEstado(Ticket.EstadoTicket.valueOf(estado.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado de ticket inválido: " + estado);
        }
    }

    public List<Ticket> obtenerTicketsPorCliente(Long clienteId) {
        return ticketRepository.findByClienteId(clienteId);
    }

    public Map<String, Long> contarTicketsPorEstado() {
        List<Object[]> resultados = ticketRepository.contarTicketsPorEstado();
        return resultados.stream()
                .collect(Collectors.toMap(
                        result -> result[0] instanceof Ticket.EstadoTicket ? ((Ticket.EstadoTicket) result[0]).name() : String.valueOf(result[0]),
                        result -> (Long) result[1]
                ));
    }

    // Mutations
    public Ticket crearTicket(TicketInput input) {
        Ticket ticket = new Ticket();
        ticket.setTitulo(input.getTitulo());
        ticket.setDescripcion(input.getDescripcion());
        ticket.setPrioridad(convertirPrioridad(input.getPrioridad()));
        ticket.setClienteId(input.getClienteId());
        ticket.setAsignadoA(input.getAsignadoA());
        ticket.setEstado(Ticket.EstadoTicket.ABIERTO);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarTicket(Long id, String titulo, String descripcion,
                                   String estado, String prioridad, String asignadoA) {

        return ticketRepository.findById(id).map(ticket -> {
            if (titulo != null) ticket.setTitulo(titulo);
            if (descripcion != null) ticket.setDescripcion(descripcion);
            if (estado != null) ticket.setEstado(Ticket.EstadoTicket.valueOf(estado.toUpperCase()));
            if (prioridad != null) ticket.setPrioridad(convertirPrioridad(prioridad));
            if (asignadoA != null) ticket.setAsignadoA(asignadoA);

            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));
    }

    public Ticket cambiarEstadoTicket(Long id, String nuevoEstado) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setEstado(Ticket.EstadoTicket.valueOf(nuevoEstado.toUpperCase()));
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));
    }

    public boolean eliminarTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método de utilidad
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Métodos auxiliares
    private Ticket.PrioridadTicket convertirPrioridad(String prioridad) {
        if (prioridad == null || prioridad.trim().isEmpty()) {
            return Ticket.PrioridadTicket.MEDIA;
        }

        return switch (prioridad.toUpperCase()) {
            case "BAJA" -> Ticket.PrioridadTicket.BAJA;
            case "MEDIA" -> Ticket.PrioridadTicket.MEDIA;
            case "ALTA" -> Ticket.PrioridadTicket.ALTA;
            case "CRITICA" -> Ticket.PrioridadTicket.CRITICA;
            default -> throw new IllegalArgumentException("Prioridad inválida: " + prioridad);
        };
    }
}