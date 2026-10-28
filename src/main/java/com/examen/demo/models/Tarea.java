package com.examen.demo.models;

import com.examen.demo.models.enums.EstadoTarea;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tareas")
@Data
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    // Relación con el proyecto al que pertenece
    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;
}