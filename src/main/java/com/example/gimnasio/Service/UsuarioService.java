package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Models.Entrenador;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario loguin(LoguinDTO loguinDTO){
        return repo.findByEmailAndPassword(loguinDTO.email(),loguinDTO.password()).orElseThrow(() -> new RuntimeException("No existe ningun usuario con este email o contraseña"));
    }
    public List<Usuario> listarTodosLosUsuarios() {
        List<Usuario> lista = repo.findAll();
        if (lista.isEmpty()) {
            throw new RuntimeException("No hay usuarios registrados en el gimnasio actualmente.");
        }
        return lista;
    }
    public Usuario buscarPorId(int idUsuario){
        return repo.findById(idUsuario).orElseThrow(() -> new RuntimeException("No existe ningun usuario con ese email o contraseña"));
    }
    public void guardar(Usuario usuario){
        repo.save(usuario);
    }

}
