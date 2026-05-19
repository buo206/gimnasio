package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.RegistroUsuarioBonoDTO;
import com.example.gimnasio.Repository.RegistroUsuarioBonoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroUsuarioBonoService {
    private final  RegistroUsuarioBonoRepository repo ;

    public RegistroUsuarioBonoService(RegistroUsuarioBonoRepository repo) {
        this.repo = repo;
    }

    public List<RegistroUsuarioBonoDTO> listarPorUsuario(int idusuario){
        List<RegistroUsuarioBonoDTO> lista = repo.buscarBonosPorUsuario(idusuario);
        if(lista.isEmpty()){
            throw new RuntimeException("Este usuario no tiene ningun bono");
        }
        return lista;
    }
}
