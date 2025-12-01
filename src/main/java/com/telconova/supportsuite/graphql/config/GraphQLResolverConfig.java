package com.telconova.supportsuite.graphql.config;

import com.telconova.supportsuite.graphql.resolver.TicketQueryResolver;
import com.telconova.supportsuite.graphql.resolver.TicketMutationResolver;
import com.telconova.supportsuite.graphql.resolver.TicketResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLResolverConfig {

    @Bean
    public RuntimeWiringConfigurer wiringConfigurer(
            TicketQueryResolver ticketQueryResolver,
            TicketMutationResolver ticketMutationResolver,
            TicketResolver ticketResolver
    ) {
        return wiringBuilder -> wiringBuilder
                // Consultas (Query)
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("obtenerTickets", env -> ticketQueryResolver.obtenerTickets())
                        .dataFetcher("obtenerTicketPorId", env -> ticketQueryResolver.obtenerTicketPorId(env.getArgument("id")))
                        .dataFetcher("obtenerTicketsPorEstado", env -> ticketQueryResolver.obtenerTicketsPorEstado(env.getArgument("estado")))
                        .dataFetcher("obtenerTicketsPorCliente", env -> ticketQueryResolver.obtenerTicketsPorCliente(env.getArgument("clienteId")))
                        .dataFetcher("contarTicketsPorEstado", env -> ticketQueryResolver.contarTicketsPorEstado())
                )
                // Mutaciones (Mutation)
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("crearTicket", env -> ticketMutationResolver.crearTicket(env.getArgument("input")))
                        .dataFetcher("actualizarTicket", env -> ticketMutationResolver.actualizarTicket(
                                env.getArgument("titulo"), env.getArgument("descripcion"),
                                env.getArgument("estado"), env.getArgument("prioridad"),
                                env.getArgument("asignadoA"), env.getArgument("id")
                        ))
                        .dataFetcher("cambiarEstadoTicket", env -> ticketMutationResolver.cambiarEstadoTicket(
                                env.getArgument("id"), env.getArgument("estado")
                        ))
                        .dataFetcher("eliminarTicket", env -> ticketMutationResolver.eliminarTicket(env.getArgument("id")))
                        .dataFetcher("agregarComentario", env -> ticketMutationResolver.agregarComentario(
                                env.getArgument("ticketId"), env.getArgument("autor"), env.getArgument("contenido")
                        ))
                )
                // Field Resolver (para el campo 'comentarios' dentro del tipo 'Ticket')
                .type("Ticket", typeWiring -> typeWiring
                        .dataFetcher("comentarios", env -> ticketResolver.comentarios(env.getSource()))
                );
    }
}