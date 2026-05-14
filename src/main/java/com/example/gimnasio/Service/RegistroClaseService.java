package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.Repository.RegistroClaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistroClaseService {
    private final RegistroClaseRepository repo ;

    public RegistroClaseService(RegistroClaseRepository repo) {
        this.repo = repo;
    }

    public List<ListaRegistroClaseUsuarioDTO> listarRegistroPorUsuario(int idUsuario){

        List<ListaRegistroClaseUsuarioDTO> lista = repo.bucarClasesPorUsuario(idUsuario);
        if(lista.isEmpty()){
            throw new RuntimeException("Este usuario no tiene ninguna clase agenciadad");
        }

        return lista ;
    }
}
