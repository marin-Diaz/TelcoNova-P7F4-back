package com.telconova.supportsuite.graphql.service;

import com.telconova.supportsuite.graphql.entity.Comentario;
import com.telconova.supportsuite.graphql.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> obtenerComentariosPorTicket(Long ticketId) {
        return comentarioRepository.findByTicketIdOrdered(ticketId);
    }

    public Comentario agregarComentario(Long ticketId, String autor, String contenido) {
        Comentario comentario = new Comentario();
        comentario.setTicketId(ticketId);
        comentario.setAutor(autor);
        comentario.setContenido(contenido);

        return comentarioRepository.save(comentario);
    }
}
