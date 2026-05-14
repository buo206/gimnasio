package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrenadorSevice {
    private final EntrenadorRepository repo ;

    public EntrenadorSevice(EntrenadorRepository repo) {
        this.repo = repo;
    }

    public EntrenadorDTO loguin(LoguinDTO logueo){
        return repo.findByEmailAndPassword(logueo.email() , logueo.password()).orElseThrow(() -> new RuntimeException("No existe ningun trabajador con ese email o contraseña"));
    }
}
