package com.examen.demo.services;

import com.examen.demo.models.Usuario;
import com.examen.demo.models.enums.RolUsuario;
import com.examen.demo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (usuario.getRol() == null) {
            usuario.setRol(RolUsuario.USUARIO);
        }

        return usuarioRepository.save(usuario);
    }
}