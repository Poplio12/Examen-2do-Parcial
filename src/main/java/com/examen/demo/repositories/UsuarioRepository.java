package com.examen.demo.repositories;
import com.examen.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username); // Útil para el login y JWT
}