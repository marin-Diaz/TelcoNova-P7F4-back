package com.telconova.supportsuite.repository;

import com.telconova.supportsuite.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // Métodos de consulta futuros para el panel de auditoría (si fueran necesarios)
}
