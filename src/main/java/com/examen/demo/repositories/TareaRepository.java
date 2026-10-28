package com.examen.demo.repositories;
import com.examen.demo.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {}