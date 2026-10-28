package com.examen.demo.services;

import com.examen.demo.models.Proyecto;
import com.examen.demo.repositories.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public List<Proyecto> obtenerProyectosPorUsuario(Long usuarioId) {
        return proyectoRepository.findByUsuarioId(usuarioId);
    }

    public Proyecto crearProyecto(Proyecto proyecto, Long usuarioId) {
        // solo 3 proyectos por usuario
        long proyectosActivos = proyectoRepository.countByUsuarioId(usuarioId);

        if (proyectosActivos >= 3) {
            throw new RuntimeException("no se pueden más de 3 proyectos");
        }

        return proyectoRepository.save(proyecto);
    }
}