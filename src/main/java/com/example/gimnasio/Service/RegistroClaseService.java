package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.Models.Clase;
import com.example.gimnasio.Models.RegistroClase;
import com.example.gimnasio.Models.RegistroUsuarioBono;
import com.example.gimnasio.Repository.ClaseRepository;
import com.example.gimnasio.Repository.RegistroClaseRepository;
import com.example.gimnasio.Repository.RegistroUsuarioBonoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroClaseService {
    private final RegistroClaseRepository repo ;
    private final RegistroUsuarioBonoRepository repoRegistroBono ;

    public RegistroClaseService(RegistroClaseRepository repo,  RegistroUsuarioBonoRepository repoRegistroBono) {
        this.repo = repo;
        this.repoRegistroBono = repoRegistroBono;
    }

    public List<ListaRegistroClaseUsuarioDTO> listarRegistroPorUsuario(int idUsuario){

        List<ListaRegistroClaseUsuarioDTO> lista = repo.bucarClasesPorUsuario(idUsuario);
        if(lista.isEmpty()){
            throw new RuntimeException("Este usuario no tiene ninguna clase agenciadad");
        }

        return lista ;
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
