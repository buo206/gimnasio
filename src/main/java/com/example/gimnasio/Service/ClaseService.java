package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Repository.ClaseRepository;
import com.example.gimnasio.Repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {
    private final ClaseRepository repo;
    private final EntrenadorRepository repoEntrenador;

    public ClaseService(ClaseRepository repo, EntrenadorRepository repoEntrenador) {
        this.repo = repo;
        this.repoEntrenador = repoEntrenador;
    }
    public List<ListaClaseEntrenadorDTO> listaClaseEntrenador(int idEntrenador){
        List<ListaClaseEntrenadorDTO> lista = repo.obtenerlista(idEntrenador);
        if(lista.isEmpty()){
            throw new RuntimeException("No se puede ver la lista de Clases");
        }
        return lista;
    }

    public List<ListaClaseEntrenadorDTO> listaClasesDisponiblesParaUsuario(int idEntrenador, int idUsuario){
        List<ListaClaseEntrenadorDTO> lista = repo.obtenerClasesDisponiblesPorEntrenadorYUsuario(idEntrenador, idUsuario);
        if(lista.isEmpty()){
            throw new RuntimeException("No hay clases disponibles para este usuario con ese entrenador");
        }
        return lista;
    }

    public List<EntrenadorDTO> listaEntrenadores(){
        List<EntrenadorDTO> lista = repoEntrenador.findAllEntrenadores();
        if(lista.isEmpty()){
            throw new RuntimeException("No hay entrenadores disponibles");
        }
        return lista ;
    }
}
