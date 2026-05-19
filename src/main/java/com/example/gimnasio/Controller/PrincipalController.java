package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.Models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
    @GetMapping("")
    public String iniciar(HttpSession session){
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if(entrenador == null){
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            if(usuario ==null){
                return "redirect:/entrenador" ;
            }
            return "redirect:/usuario" ;
        }
        return "redirect:/entrenador" ;
    }
}
