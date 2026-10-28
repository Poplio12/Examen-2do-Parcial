package com.examen.demo.controllers;

import com.examen.demo.models.Proyecto;
import com.examen.demo.services.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> listarProyectos(@RequestParam Long usuarioId) {
        List<Proyecto> proyectos = proyectoService.obtenerProyectosPorUsuario(usuarioId);
        return ResponseEntity.ok(proyectos);
    }

    @PostMapping
    public ResponseEntity<?> crearProyecto(@RequestBody Proyecto proyecto, @RequestParam Long usuarioId) {
        try {
            Proyecto nuevoProyecto = proyectoService.crearProyecto(proyecto, usuarioId);
            return ResponseEntity.status(201).body(nuevoProyecto);
        } catch (RuntimeException e) {
            // po si infringe la regla de los 3 proyectos
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}