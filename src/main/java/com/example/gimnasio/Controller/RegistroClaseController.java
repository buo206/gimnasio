package com.example.gimnasio.Controller;


import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Service.RegistroClaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/registroClase")
public class RegistroClaseController {
    private final RegistroClaseService servicio ;

    public RegistroClaseController(RegistroClaseService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listaClasesUsuario/{idUsuario}")
    public String listaClases(@PathVariable int idUsuario , HttpSession session, Model model , RedirectAttributes redirectAttributes) {
        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        try {
            List<ListaRegistroClaseUsuarioDTO>  listaClases= servicio.listarRegistroPorUsuario(idUsuario);
            model.addAttribute("listaClases", listaClases);
            return "ListaClasesUsuario";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "ListaClasesUsuario";
        }

    }
}
