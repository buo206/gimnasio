package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.ListaClaseConTipoBonoDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.Clase;
import com.example.gimnasio.Repository.ClaseRepository;
import com.example.gimnasio.Repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {
    private final ClaseRepository repo;

    public ClaseService(ClaseRepository repo) {
        this.repo = repo;
    }
    public List<ListaClaseConTipoBonoDTO> listaClaseEntrenador(int idEntrenador){
        List<ListaClaseConTipoBonoDTO> lista = repo.obtenerClaseConTipoBono(idEntrenador);
        if(lista.isEmpty()){
            throw new RuntimeException("No se puede ver la lista de Clases");
        }
        return lista;
    }
    public Clase buscarPorId(int idClase) {
        return repo.findById(idClase)
                .orElseThrow(() -> new RuntimeException("No se encontró la clase con el ID: " + idClase));
    }
    public List<DetalleClaseDTO> obtenerDetalleClaseUsuario(int idClase) {
        return repo.obtenerDetalleClase(idClase);
    }

    public List<ListaClaseEntrenadorDTO> listaClasesDisponiblesParaUsuario(int idUsuario){
        List<ListaClaseEntrenadorDTO> lista = repo.obtenerClasesDisponiblesPorEntrenadorYUsuario(idUsuario);
        if(lista.isEmpty()){
            throw new RuntimeException("No hay clases disponibles para este usuario con ese entrenador");
        }
        return lista;
    }
    public void guardar(Clase clase){
        repo.save(clase);
    }


}
