package com.example.gimnasio.Service;

import com.example.gimnasio.Repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

@Service
public class EntrenadorSevice {
    private final EntrenadorRepository repo ;

    public EntrenadorSevice(EntrenadorRepository repo) {
        this.repo = repo;
    }


}
