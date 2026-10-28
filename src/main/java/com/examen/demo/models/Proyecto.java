package com.examen.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "proyectos")
@Data
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relación con el usuario propietario del proyecto
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}