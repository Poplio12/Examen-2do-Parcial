package com.examen.demo.services;

import com.examen.demo.models.Tarea;
import com.examen.demo.models.enums.EstadoTarea;
import com.examen.demo.repositories.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;

    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizarEstado(Long id, EstadoTarea nuevoEstado) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));

        tarea.setEstado(nuevoEstado);
        return tareaRepository.save(tarea);
    }
}