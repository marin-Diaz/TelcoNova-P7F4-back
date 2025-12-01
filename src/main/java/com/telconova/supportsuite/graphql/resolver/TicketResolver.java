package com.telconova.supportsuite.graphql.resolver;

import com.telconova.supportsuite.graphql.entity.Ticket;
import com.telconova.supportsuite.graphql.entity.Comentario;
import com.telconova.supportsuite.graphql.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketResolver {

    @Autowired

    private final ComentarioService comentarioService;

    public TicketResolver(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Resolver para el campo 'comentarios' de la entidad Ticket
    // Source indica que se resuelve el tipo "Ticket"
    @SchemaMapping(typeName = "Ticket")
    public List<Comentario> comentarios(Ticket ticket) {
        // En un Field Resolver, el objeto padre (Ticket) ya está cargado.
        // Usamos su ID para cargar la relación (comentarios) de forma perezosa.
        return comentarioService.obtenerComentariosPorTicket(ticket.getId());
    }
}