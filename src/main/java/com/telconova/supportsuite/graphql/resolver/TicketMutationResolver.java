package com.telconova.supportsuite.graphql.resolver;

import com.telconova.supportsuite.graphql.dto.TicketInput;
import com.telconova.supportsuite.graphql.entity.Comentario;
import com.telconova.supportsuite.graphql.entity.Ticket;
import com.telconova.supportsuite.graphql.service.ComentarioService;
import com.telconova.supportsuite.graphql.service.TicketGraphqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TicketMutationResolver {

    @Autowired
    private final TicketGraphqlService ticketGraphqlService;
    @Autowired
    private final ComentarioService comentarioService;

    public TicketMutationResolver(TicketGraphqlService ticketGraphqlService, ComentarioService comentarioService) {
        this.ticketGraphqlService = ticketGraphqlService;
        this.comentarioService = comentarioService;
    }

    @MutationMapping
    public Ticket crearTicket(@Argument TicketInput input) {
        return ticketGraphqlService.crearTicket(input);
    }

    @MutationMapping
    public Ticket actualizarTicket(
            @Argument String titulo,
            @Argument String descripcion,
            @Argument String estado,
            @Argument String prioridad,
            @Argument String asignadoA,
            @Argument Long id) {

        return ticketGraphqlService.actualizarTicket(id, titulo, descripcion, estado, prioridad, asignadoA);
    }

    @MutationMapping
    public Ticket cambiarEstadoTicket(@Argument Long id, @Argument String estado) {
        return ticketGraphqlService.cambiarEstadoTicket(id, estado);
    }

    @MutationMapping
    public boolean eliminarTicket(@Argument Long id) {
        return ticketGraphqlService.eliminarTicket(id);
    }

    @MutationMapping
    public Comentario agregarComentario(
            @Argument Long ticketId,
            @Argument String autor,
            @Argument String contenido) {

        return comentarioService.agregarComentario(ticketId, autor, contenido);
    }
}
