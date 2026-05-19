package com.example.gimnasio.ControllerRes;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.DTO.RegistroUsuarioBonoDTO;
import com.example.gimnasio.Service.RegistroUsuarioBonoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/res/registroUsuarioBono")
public class RegistroUsuarioBonoResController {
    private final RegistroUsuarioBonoService servicio ;

    public RegistroUsuarioBonoResController(RegistroUsuarioBonoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listaRegistroBonoPorUsuario/{idUsuario}")
    public ResponseEntity<List<RegistroUsuarioBonoDTO>> listar(@PathVariable int idUsuario) {
        try{
            List<RegistroUsuarioBonoDTO>  lista= servicio.listarPorUsuario(idUsuario);
            return ResponseEntity.ok(lista);
        }catch (RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ex.getMessage());

        }


    }
}
