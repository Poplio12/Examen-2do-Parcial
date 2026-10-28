package com.examen.demo.repositories;
import com.examen.demo.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByUsuarioId(Long usuarioId); // Para listar solo los proyectos del usuario autenticado
    long countByUsuarioId(Long usuarioId);         // Clave para validar la regla de negocio de los 3 proyectos
}