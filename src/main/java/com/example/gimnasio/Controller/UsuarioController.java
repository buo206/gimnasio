package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Models.Entrenador;
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

    @GetMapping("")
    public String iniciar(Model model ,HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            model.addAttribute("loguin", new LoguinDTO("","") );
            return "UsuarioPrincipal.html";
        }
        return "redirect:/usuario/home";
    }

    @PostMapping("/loguin")
    public String loguin(
            @ModelAttribute LoguinDTO loguinDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Usuario usuario = servicio.loguin(loguinDTO);

            session.setAttribute("usuarioLogueado", usuario);

            return "redirect:/usuario/home";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuario";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        model.addAttribute("usuario", usuario);
        return "UsuarioMenu";
    }




    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuario";
    }
    @GetMapping("/editarPerfil")
    public String editarPerfilUsuario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }
        Usuario usuarioModelo = servicio.buscarPorId(usuario.getIdUsuario());
        model.addAttribute("usuario", usuarioModelo);
        return "EditarUsuario";

    }
    @PostMapping("/guardarPerfil")
    public String guardarPerfil(HttpSession session, Model model,Usuario usuarioModelo, RedirectAttributes redirectAttributes){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }
        servicio.guardar(usuarioModelo);

        Usuario nuevoUsuario = new Usuario(
                usuarioModelo.getIdUsuario(),
                usuarioModelo.getNombre(),
                usuarioModelo.getApellidos(),
                usuarioModelo.getDireccion(),
                usuarioModelo.getEmail(),
                usuarioModelo.getPassword(),
                usuarioModelo.getDni(),
                usuarioModelo.getTelefono()

        );
        servicio.guardar(nuevoUsuario);
        session.setAttribute("usuarioLogueado",nuevoUsuario);
        redirectAttributes.addFlashAttribute("mensajeExito", "Perfil actualizado correctamente");
        return "redirect:/usuario/home";
    }

}
