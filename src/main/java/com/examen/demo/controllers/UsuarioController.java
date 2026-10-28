package com.examen.demo.controllers;

import com.examen.demo.config.JwtUtil;
import com.examen.demo.models.Usuario;
import com.examen.demo.repositories.UsuarioRepository;
import com.examen.demo.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        // Encripta la contraseña antes de guardar (si no lo hace ya el service)
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        // Usamos "username" porque es el campo que tiene tu entidad Usuario
        String username = credenciales.get("username");
        String password = credenciales.get("password");

        var usuarioOpt = usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                String token = jwtUtil.generarToken(usuario.getUsername());
                return ResponseEntity.ok(Collections.singletonMap("token", token));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}