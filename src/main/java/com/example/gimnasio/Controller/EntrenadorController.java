package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Models.Entrenador;
import com.example.gimnasio.Service.EntrenadorSevice;
import com.example.gimnasio.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {
    private final EntrenadorSevice servicio;
    private final UsuarioService usuService;

    public EntrenadorController(EntrenadorSevice servicio, UsuarioService usuService) {
        this.servicio = servicio;
        this.usuService = usuService;
    }

    @GetMapping("")
    public String iniciar(Model model, HttpSession session) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            model.addAttribute("loguin", new LoguinDTO("", ""));
            return "Principal.html";
        }
        return "redirect:/entrenador/home";
    }

    @PostMapping("/loguin")
    public String loguin(
            @ModelAttribute LoguinDTO loginDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        try {
            EntrenadorDTO entrenador = servicio.loguin(loginDTO);

            session.setAttribute("entrenadorLogueado", entrenador);

            return "redirect:/entrenador/home";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/entrenador";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            return "redirect:/entrenador";
        }

        model.addAttribute("entrenador", entrenador);
        return "EntrenadorMenu";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/entrenador";
    }

    @GetMapping("/lista")
    public String lista(HttpSession session, Model model) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            return "redirect:/entrenador";
        }

        model.addAttribute("entrenador", entrenador);
        return "redirect:/clase/listaClases";
    }

    @GetMapping("/usuarios")
    public String listarUsuariosGimnasio(HttpSession session, Model model) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }

        try {
            model.addAttribute("entrenador", entrenador);
            model.addAttribute("listaUsuarios", usuService.listarTodosLosUsuarios());
        } catch (RuntimeException e) {
            model.addAttribute("errorMensaje", e.getMessage());
        }

        return "ListaUsuarios";
    }

    @GetMapping("/editarPerfil")
    public String editarPerfilEntrenador(HttpSession session, Model model) {
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }
        Entrenador entrenadorModelo = servicio.buscarPorId(entrenador.idEntrenador());
        model.addAttribute("entrenador", entrenadorModelo);
        return "EditarEntrenador";

    }
    @PostMapping("/guardarPerfil")
    public String guardarPerfil(HttpSession session, Model model,Entrenador entrenadorModelo, RedirectAttributes redirectAttributes){
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");
        if (entrenador == null) {
            return "redirect:/entrenador/loguin";
        }
        servicio.guardar(entrenadorModelo);

        EntrenadorDTO nuevoDTO = new EntrenadorDTO(
                entrenadorModelo.getIdEntranador(),
                entrenadorModelo.getNombre(),
                entrenadorModelo.getApellidos(),
                entrenadorModelo.getDireccion(),
                entrenadorModelo.getEmail(),
                entrenadorModelo.getPassword(),
                entrenadorModelo.getTelefono()
        );
        session.setAttribute("entrenadorLogueado",nuevoDTO);
        redirectAttributes.addFlashAttribute("mensajeExito", "Perfil actualizado correctamente");
        return "redirect:/entrenador/home";
    }

}