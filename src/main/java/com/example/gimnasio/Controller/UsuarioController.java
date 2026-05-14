package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Repository.UsuarioRepository;
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
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/loguin")
    public String loguin(
            @ModelAttribute LoguinDTO loguinDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Usuario usuario = servicio.loguin(loguinDTO);

            session.setAttribute("usarioLogueado", usuario);

            return "redirect:/usuario/home";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuario/loguin";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Usuario usuario =
                (Usuario) session.getAttribute("usarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario/loguin";
        }

        model.addAttribute("usuario", usuario);
        return "usuario/home";
    }

}
