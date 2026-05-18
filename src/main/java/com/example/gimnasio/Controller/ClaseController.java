package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.Clase;
import com.example.gimnasio.Service.ClaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
            return "redirect:/entrenador/loguin";
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
    @GetMapping("/detalle/{idClase}")
    public String detalleClase(@PathVariable("idClase") int idClase,HttpSession session, Model model){
        EntrenadorDTO entrenador =(EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }
        try {
            Clase clase = servicio.buscarPorId(idClase);
            model.addAttribute("clase", clase);

            List<DetalleClaseDTO> usuarios = servicio.obtenerDetalleClaseUsuario(idClase);
            model.addAttribute("listaUsuarios", usuarios);

        } catch (RuntimeException e) {
            model.addAttribute("errorMensaje", e.getMessage());
        }

        model.addAttribute("entrenador", entrenador);
        return "DetalleClaseEntrenador";
    }
}
