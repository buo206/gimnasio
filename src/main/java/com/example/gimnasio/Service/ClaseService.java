package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {
    private final ClaseRepository repo;

    public ClaseService(ClaseRepository repo) {
        this.repo = repo;
    }
    public List<ListaClaseEntrenadorDTO> listaClaseEntrenador(int idEntrenador){
        List<ListaClaseEntrenadorDTO> lista = repo.obtenerlista(idEntrenador);
        if(lista.isEmpty()){
            throw new RuntimeException("No se puede ver la lista de Clases");
        }
        return lista;
    }
}
