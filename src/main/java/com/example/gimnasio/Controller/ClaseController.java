package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.ListaClaseConTipoBonoDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.TipoBono;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Models.Clase;
import com.example.gimnasio.Service.ClaseService;
import com.example.gimnasio.Service.TipoBonoService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.engine.spi.EntityEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clase")
public class ClaseController {
    private final ClaseService servicio;
    private final TipoBonoService servicioBono;

    public ClaseController(ClaseService servicio, TipoBonoService servicioBono) {
        this.servicio = servicio;
        this.servicioBono = servicioBono;
    }

    @GetMapping("/listaClases")
    public String listarClasesEntrenador(HttpSession session, Model model){
        EntrenadorDTO entrenador =(EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            return "redirect:/entrenador";
        }
        try{
            List<ListaClaseConTipoBonoDTO> clases = servicio.listaClaseEntrenador(entrenador.idEntrenador());
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
            return "FormularioNuevoRegistroClase";
        }

        model.addAttribute("idUsuario", idUsuario);
        return "FormularioClase";
    }
    @GetMapping("/editar/{idClase}")
    public String mostrarFormularioEditar(@PathVariable("idClase") int idClase, HttpSession session, Model model) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }

        try {
            Clase clase = servicio.buscarPorId(idClase);
            model.addAttribute("clase", clase);
            List<TipoBono> listaBonos = servicioBono.listarBonos();
            model.addAttribute("listaBonos",listaBonos);
        } catch (RuntimeException e) {
            model.addAttribute("errorMensaje", e.getMessage());
            return "redirect:/clase/listaClases";
        }

        model.addAttribute("entrenador", entrenador);
        return "FormularioClase";
    }

    @PostMapping("/actualizar")
    public String actualizarClase(Clase clase, HttpSession session, RedirectAttributes redirectAttributes) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }

        try {
            Clase claseOriginal = servicio.buscarPorId(clase.getIdClase());
            claseOriginal.setTitulo(clase.getTitulo());
            claseOriginal.setFecha(clase.getFecha());
            claseOriginal.setHora(clase.getHora());
            claseOriginal.setCupoMax(clase.getCupoMax());
            claseOriginal.setIdBono(clase.getIdBono());
            servicio.guardar(claseOriginal);
            redirectAttributes.addFlashAttribute("mensajeExito", "Clase actualizada");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMensaje", "Error al actualizar la clase:");
        }

        return "redirect:/clase/listaClases";
    }
}
