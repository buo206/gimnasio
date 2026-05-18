package com.example.gimnasio.Service;

import com.example.gimnasio.Models.TipoBono;
import com.example.gimnasio.Repository.TipoBonoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoBonoService {
    private final TipoBonoRepository repo;

    public TipoBonoService(TipoBonoRepository repo) {
        this.repo = repo;
    }
    public List<TipoBono> listarBonos(){
        return repo.findAll();
    }
}
