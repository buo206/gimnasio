package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.ListaClaseConTipoBonoDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.DTO.RegistroClaseDTO;
import com.example.gimnasio.Models.*;
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
        Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }
        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("registroClase", new RegistroClaseDTO(0, 0, 0, Estado.PENDIENTE));
        try{
            List<ListaClaseEntrenadorDTO> clases = servicio.listaClasesDisponiblesParaUsuario(idUsuario);
            model.addAttribute("listaClases", clases);
        }catch (RuntimeException e){
            model.addAttribute("errorMensaje", e.getMessage());
            return "FormularioNuevoRegistroClase";
        }

        return "FormularioNuevoRegistroClase";
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
    public String guardarOActualizarClase(Clase clase, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }

        if (clase.getFecha() == null || !clase.getFecha().isAfter(java.time.LocalDate.now())) {
            model.addAttribute("errorMensaje", "La fecha de la sesión debe ser posterior a la fecha actual del sistema.");
            model.addAttribute("clase", clase);
            model.addAttribute("listaBonos", servicioBono.listarBonos());
            model.addAttribute("entrenador", entrenador);
            return "FormularioClase";
        }

        try {
            if (clase.getIdClase() > 0) {
                Clase claseOriginal = servicio.buscarPorId(clase.getIdClase());
                if (claseOriginal.getIdBono() != null && claseOriginal.getIdBono().getIdBono() == 2 && clase.getCupoMax() > 1) {
                    model.addAttribute("errorMensaje", "El Tipo 2 de bono es para clases individuales. El cupo máximo permitido es de 1 persona.");
                    model.addAttribute("clase", clase);
                    model.addAttribute("listaBonos", servicioBono.listarBonos());
                    model.addAttribute("entrenador", entrenador);
                    return "FormularioClase";
                }
                claseOriginal.setTitulo(clase.getTitulo());
                claseOriginal.setFecha(clase.getFecha());
                claseOriginal.setHora(clase.getHora());
                claseOriginal.setCupoMax(clase.getCupoMax());

                servicio.guardar(claseOriginal);
                redirectAttributes.addFlashAttribute("mensajeExito", "Clase actualizada con éxito");

            } else {
                if (clase.getIdBono() != null && clase.getIdBono().getIdBono() == 2 && clase.getCupoMax() != 1) {
                    model.addAttribute("errorMensaje", "El Tipo 2 de bono solo permite un cupo máximo de 1 persona.");
                    model.addAttribute("clase", clase);
                    model.addAttribute("listaBonos", servicioBono.listarBonos());
                    model.addAttribute("entrenador", entrenador);
                    return "FormularioClase";
                }
                Entrenador entrenadorModelo = new Entrenador();
                entrenadorModelo.setIdEntranador(entrenador.idEntrenador());

                clase.setEntrenador(entrenadorModelo);

                servicio.guardar(clase);
                redirectAttributes.addFlashAttribute("mensajeExito", "Nueva clase creada con éxito");
            }
        } catch (RuntimeException e) {
            model.addAttribute("errorMensaje", "Error en el sistema: " + e.getMessage());
            model.addAttribute("clase", clase);
            model.addAttribute("listaBonos", servicioBono.listarBonos());
            model.addAttribute("entrenador", entrenador);
            return "FormularioClase";
        }

        return "redirect:/clase/listaClases";
    }
    @GetMapping("/nuevo")
    public String NuevaClase(HttpSession session, Model model){
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }
            model.addAttribute("clase", new Clase());
            model.addAttribute("listaBonos", servicioBono.listarBonos());
            model.addAttribute("entrenador", entrenador);
            return "FormularioClase";
        }
    @PostMapping("/detalle/{idClase}/terminar")
    public String terminarClaseAction(@PathVariable("idClase")int idClase, RedirectAttributes redirectAttributes) {
        try {
            servicio.terminarClase(idClase);
            redirectAttributes.addFlashAttribute("mensajeExito", "Clase finalizada. Todas las reservas han pasado a CANCELADA.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMensaje", "Error al finalizar la clase: " + e.getMessage());
        }
        return "redirect:/clase/detalle/" + idClase;
    }
    @PostMapping("/detalle/{idClase}/registroBono/{idRegistroBono}/confirmar")
    public String confirmarUsuario(@PathVariable("idClase") int idClase,@PathVariable("idRegistroBono") int idRegistroBono, RedirectAttributes redirectAttributes){
        try {
            servicio.cambiarEstadoUsuario(idClase, idRegistroBono, Estado.CONFIRMADA);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMensaje", e.getMessage());
        }
        return "redirect:/clase/detalle/" + idClase;
    }
    @PostMapping("/detalle/{idClase}/registroBono/{idRegistroBono}/cancelar")
    public String cancelarUsuario(@PathVariable("idClase") int idClase,@PathVariable("idRegistroBono") int idRegistroBono,RedirectAttributes redirectAttributes){
        try {
            servicio.cambiarEstadoUsuario(idClase, idRegistroBono, Estado.CANCELADA);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMensaje", e.getMessage());
        }
        return "redirect:/clase/detalle/" + idClase;
    }
}
