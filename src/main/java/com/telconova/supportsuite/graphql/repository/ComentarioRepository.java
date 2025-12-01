package com.telconova.supportsuite.graphql.repository;

import com.telconova.supportsuite.graphql.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTicketId(Long ticketId);

    @Query("SELECT c FROM Comentario c WHERE c.ticketId = :ticketId ORDER BY c.fechaCreacion DESC")
    List<Comentario> findByTicketIdOrdered(@Param("ticketId") Long ticketId);
}
