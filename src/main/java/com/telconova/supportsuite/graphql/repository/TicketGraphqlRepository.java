package com.telconova.supportsuite.graphql.repository;

import com.telconova.supportsuite.graphql.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketGraphqlRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEstado(Ticket.EstadoTicket estado);

    List<Ticket> findByClienteId(Long clienteId);

    Optional<Ticket> findById(Long id);

    @Query("SELECT t.estado as estado, COUNT(t) as total FROM Ticket t GROUP BY t.estado")
    List<Object[]> contarTicketsPorEstado();

    @Query("SELECT t FROM Ticket t WHERE t.estado = :estado OR :estado IS NULL")
    List<Ticket> findByEstadoOrAll(@Param("estado") Ticket.EstadoTicket estado);
}