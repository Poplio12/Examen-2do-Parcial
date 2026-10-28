package com.examen.demo.controllers;

import com.examen.demo.models.Tarea;
import com.examen.demo.models.enums.EstadoTarea;
import com.examen.demo.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return ResponseEntity.status(201).body(nuevaTarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarEstado(@PathVariable Long id, @RequestParam EstadoTarea estado) {
        Tarea tareaActualizada = tareaService.actualizarEstado(id, estado);
        return ResponseEntity.ok(tareaActualizada);
    }
}