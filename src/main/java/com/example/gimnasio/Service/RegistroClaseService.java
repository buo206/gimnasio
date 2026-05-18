package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.DTO.RegistroClaseDTO;
import com.example.gimnasio.Models.Clase;
import com.example.gimnasio.Models.Estado;
import com.example.gimnasio.Models.RegistroClase;
import com.example.gimnasio.Models.RegistroUsuarioBono;
import com.example.gimnasio.Repository.ClaseRepository;
import com.example.gimnasio.Repository.RegistroClaseRepository;
import com.example.gimnasio.Repository.RegistroUsuarioBonoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroClaseService {
    private final RegistroClaseRepository repo ;
    private final RegistroUsuarioBonoRepository repoRegistroBono ;
    private final ClaseRepository repoClase ;

    public RegistroClaseService(RegistroClaseRepository repo, RegistroUsuarioBonoRepository repoRegistroBono, ClaseRepository repoClase) {
        this.repo = repo;
        this.repoRegistroBono = repoRegistroBono;
        this.repoClase = repoClase;
    }

    public List<ListaRegistroClaseUsuarioDTO> listarRegistroPorUsuario(int idUsuario){

        List<ListaRegistroClaseUsuarioDTO> lista = repo.bucarClasesPorUsuario(idUsuario);
        if(lista.isEmpty()){
            throw new RuntimeException("Este usuario no tiene ninguna clase agenciadad");
        }

        return lista ;
    }

    public void crearRegistro(RegistroClaseDTO registroClaseDTO, int idUsuario) {
        Optional<Clase> clase = repoClase.findById(registroClaseDTO.idClase());
        if (clase.isEmpty()) {
            throw new RuntimeException("No existe ninguna clase con esa id");
        }

        if (repo.existsByClase_IdClaseAndRegistroUsuarioBono_Usuario_IdUsuario(registroClaseDTO.idClase(), idUsuario)) {
            throw new RuntimeException("Ya estas apuntado a esta clase");
        }

        List<RegistroUsuarioBono> bonosDisponibles = repoRegistroBono.buscarBonosDisponiblesParaClase(idUsuario, registroClaseDTO.idClase());

        if (bonosDisponibles.isEmpty()) {
            throw new RuntimeException("No tienes ningun bono disponible para esta clase");
        }

        RegistroUsuarioBono registroBono = bonosDisponibles.get(0);

        RegistroClase registro = new RegistroClase();
        registro.setClase(clase.get());
        registro.setRegistroUsuarioBono(registroBono);
        registro.setEstado(registroClaseDTO.estado() != null ? registroClaseDTO.estado() : Estado.PENDIENTE);

        repo.save(registro);

        registroBono.setUsos(registroBono.getUsos() + 1);
        repoRegistroBono.save(registroBono);
    }

    public void borrarRegistro(int idRegistro){
        Optional<RegistroClase> registro = repo.findById(idRegistro) ;
        if(registro.isEmpty()){
            throw new RuntimeException("No existe ningun registro de clase con esa id");
        }

        repo.deleteById(idRegistro);

        Optional<RegistroUsuarioBono> registroBono = repoRegistroBono.findById(registro.get().getRegistroUsuarioBono().getIdRegistroBono());
        RegistroUsuarioBono registroB = registroBono.get();
        registroB.setUsos(registroB.getUsos() - 1);
        repoRegistroBono.save(registroB);

    }
}
