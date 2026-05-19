package com.example.gimnasio.ControllerRes;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Service.RegistroClaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/res/registroClase")
public class RegistroClaseResController {
    private final RegistroClaseService servicio ;

    public RegistroClaseResController(RegistroClaseService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listaClasesUsuario/{idUsuario}")
    public ResponseEntity<List<ListaRegistroClaseUsuarioDTO>> listar(@PathVariable int idUsuario) {
        try{
            List<ListaRegistroClaseUsuarioDTO> lista = servicio.listarRegistroPorUsuario(idUsuario);
            return ResponseEntity.ok(lista);
        }catch (RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ex.getMessage());

        }


    }
}
