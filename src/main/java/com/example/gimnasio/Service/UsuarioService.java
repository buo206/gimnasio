package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario loguin(LoguinDTO loguinDTO){
        return repo.findByEmailAndPassword(loguinDTO.email(),loguinDTO.password()).orElseThrow(() -> new RuntimeException("No existe ningun usuario con este email o contraseña"));
    }
}
