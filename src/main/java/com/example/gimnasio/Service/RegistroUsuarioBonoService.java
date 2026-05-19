package com.example.gimnasio.Service;

import com.example.gimnasio.DTO.RegistroUsuarioBonoDTO;
import com.example.gimnasio.Models.RegistroUsuarioBono;
import com.example.gimnasio.Models.TipoBono;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Repository.RegistroUsuarioBonoRepository;
import com.example.gimnasio.Repository.TipoBonoRepository;
import com.example.gimnasio.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroUsuarioBonoService {
    private final  RegistroUsuarioBonoRepository repo ;
    private final UsuarioRepository repoUsuario ;
    private final TipoBonoRepository repoBono ;

    public RegistroUsuarioBonoService(RegistroUsuarioBonoRepository repo, UsuarioRepository repoUsuario, TipoBonoRepository repoBono) {
        this.repo = repo;
        this.repoUsuario = repoUsuario;
        this.repoBono = repoBono;
    }

    public List<RegistroUsuarioBonoDTO> listarPorUsuario(int idusuario){
        List<RegistroUsuarioBonoDTO> lista = repo.buscarBonosPorUsuario(idusuario);
        if(lista.isEmpty()){
            throw new RuntimeException("Este usuario no tiene ningun bono");
        }
        return lista;
    }

    public RegistroUsuarioBono crearBonoTipo1(int idUsuario){
        Optional<Usuario> usuario = repoUsuario.findById(idUsuario);
        if(usuario.isEmpty()){
            throw new RuntimeException("No existe un usuario con esa id");
        }

        Optional<TipoBono> bono = repoBono.findById(1);
        if(bono.isEmpty()){
            throw new RuntimeException("No existe el tipo de bono que se quiere comprar");
        }


        RegistroUsuarioBono registro = new RegistroUsuarioBono();
        registro.setUsuario(usuario.get());
        registro.setTipoBono(bono.get());
        registro.setFechaCompra(LocalDate.now());
        registro.setUsos(0);

        RegistroUsuarioBono resultado = repo.save(registro);

        if(! repo.existsByUsuario_IdUsuarioAndTipoBono_IdBono(idUsuario, 3)){
            Optional<TipoBono> bono2 = repoBono.findById(3);
            if(bono2.isEmpty()){
                throw new RuntimeException("No existe el tipo de bono que se intenta auto implementar");
            }

            RegistroUsuarioBono registrox = new RegistroUsuarioBono();
            registrox.setUsuario(usuario.get());
            registrox.setTipoBono(bono2.get());
            registrox.setFechaCompra(LocalDate.now());
            registrox.setUsos(0);

            RegistroUsuarioBono resultado2 = repo.save(registrox);
        }

        return resultado ;
    }


    public RegistroUsuarioBono crearBonoTipo2(int idUsuario){
        Optional<Usuario> usuario = repoUsuario.findById(idUsuario);
        if(usuario.isEmpty()){
            throw new RuntimeException("No existe un usuario con esa id");
        }

        Optional<TipoBono> bono = repoBono.findById(2);
        if(bono.isEmpty()){
            throw new RuntimeException("No existe el tipo de bono que se quiere comprar");
        }


        RegistroUsuarioBono registro = new RegistroUsuarioBono();
        registro.setUsuario(usuario.get());
        registro.setTipoBono(bono.get());
        registro.setFechaCompra(LocalDate.now());
        registro.setUsos(0);

        RegistroUsuarioBono resultado = repo.save(registro);

        if(! repo.existsByUsuario_IdUsuarioAndTipoBono_IdBono(idUsuario, 3)){
            Optional<TipoBono> bono2 = repoBono.findById(3);
            if(bono2.isEmpty()){
                throw new RuntimeException("No existe el tipo de bono que se intenta auto implementar");
            }

            RegistroUsuarioBono registrox = new RegistroUsuarioBono();
            registrox.setUsuario(usuario.get());
            registrox.setTipoBono(bono2.get());
            registrox.setFechaCompra(LocalDate.now());
            registrox.setUsos(0);

            RegistroUsuarioBono resultado2 = repo.save(registrox);
        }

        return resultado ;
    }
}
