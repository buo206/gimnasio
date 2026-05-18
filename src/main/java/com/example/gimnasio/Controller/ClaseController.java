package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Models.Clase;
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


    @GetMapping("/listaClasesDisponibles/{idUsuario}")
    public String listarClasesDisponibles(
            @PathVariable int idUsuario,
            Model model ,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }
        try{
            List<ListaClaseEntrenadorDTO> clases = servicio.listaClasesDisponiblesParaUsuario(idUsuario);
            model.addAttribute("listaClases", clases);
        }catch (RuntimeException e){
            model.addAttribute("errorMensaje", e.getMessage());
        }

        model.addAttribute("idUsuario", idUsuario);
        return "FormularioNuevoRegistroClase";
    }
}
