package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Service.ClaseService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.engine.spi.EntityEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clase")
public class ClaseController {
    private final ClaseService servicio;

    public ClaseController(ClaseService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listaClases")
    public String listarClasesEntrenador(HttpSession session, Model model){
        EntrenadorDTO entrenador =(EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            return "redirect:/entrenador";
        }
        try{
            List<ListaClaseEntrenadorDTO> clases = servicio.listaClaseEntrenador(entrenador.idEntrenador());
            model.addAttribute("listaClases",clases);
        }catch (RuntimeException e){
            model.addAttribute("errorMensaje", e.getMessage());
        }

        model.addAttribute("entrenador", entrenador);
        return "ListaClasesEntrenador";
    }

    @GetMapping("/listaClasesDisponibles/{idUsuario}")
    public String listarEntrenadores(
            Model model ,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            @PathVariable String idUsuario){
        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }


        try{
            List<EntrenadorDTO> entrenadores = servicio.listaEntrenadores();
            model.addAttribute("listaEntrenadores", entrenadores);
            model.addAttribute("idUsuario", idUsuario);

        }catch (RuntimeException e){
            model.addAttribute("errorMensaje", e.getMessage());
        }

        return "ListaClasesEntrenador";
    }

    @GetMapping("/listaClasesDisponibles/{idEntrenador}/{idUsuario}")
    public String listarClasesDisponibles(
            @PathVariable int idEntrenador,
            @PathVariable int idUsuario,
            Model model
    ){
        try{
            List<ListaClaseEntrenadorDTO> clases = servicio.listaClasesDisponiblesParaUsuario(idEntrenador, idUsuario);
            model.addAttribute("listaClases", clases);
        }catch (RuntimeException e){
            model.addAttribute("errorMensaje", e.getMessage());
        }

        model.addAttribute("idEntrenador", idEntrenador);
        model.addAttribute("idUsuario", idUsuario);
        return "ListaClasesEntrenador";
    }
}
