package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.ListaClaseConTipoBonoDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.Clase;
import com.example.gimnasio.Models.Estado;
import com.example.gimnasio.Models.RegistroClase;
import com.example.gimnasio.Repository.ClaseRepository;
import com.example.gimnasio.Repository.EntrenadorRepository;
import com.example.gimnasio.Repository.RegistroClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {
    private final ClaseRepository repo;
    private final RegistroClaseRepository registroRepo;

    public ClaseService(ClaseRepository repo, RegistroClaseRepository registroRepo) {
        this.repo = repo;
        this.registroRepo = registroRepo;
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
        List<ListaClaseEntrenadorDTO> lista = repo.obtenerClasesDisponiblesPorUsuario(idUsuario);
        if(lista.isEmpty()){
            throw new RuntimeException("No hay clases disponibles para este usuario con ese entrenador");
        }
        return lista;
    }
    public void guardar(Clase clase){
        repo.save(clase);
    }

    public void terminarClase(int idClase) {
        List<RegistroClase> listaInscritos = registroRepo.findByClaseIdClase(idClase);
        for (RegistroClase registro : listaInscritos) {
            registro.setEstado(Estado.FINALIZADO);
        }
        registroRepo.saveAll(listaInscritos);
    }


    public void cambiarEstadoUsuario(int idClase, int idRegistroBono, Estado nuevoEstado) {
        RegistroClase registro = registroRepo.findByClaseIdClaseAndRegistroUsuarioBonoIdRegistroBono(idClase, idRegistroBono)
                .orElseThrow(() -> new RuntimeException("No se encontró la inscripción del alumno para esta clase."));

        registro.setEstado(nuevoEstado);
        registroRepo.save(registro);
    }

}
