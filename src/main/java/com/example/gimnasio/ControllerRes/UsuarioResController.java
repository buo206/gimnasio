package com.example.gimnasio.ControllerRes;

import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/res/usuario")
public class UsuarioResController {
    private final UsuarioService servicio ;

    public UsuarioResController(UsuarioService servicio) {
        this.servicio = servicio;
    }


    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoguinDTO login) {
        try{
            Usuario usuario = servicio.loguin(login);
            return ResponseEntity.ok(usuario);
        }catch (RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ex.getMessage());

        }
    }
}
