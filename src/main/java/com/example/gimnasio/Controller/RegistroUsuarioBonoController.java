package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.*;
import com.example.gimnasio.Models.Estado;
import com.example.gimnasio.Models.Usuario;
import com.example.gimnasio.Service.RegistroUsuarioBonoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/registroUsuarioBono")
public class RegistroUsuarioBonoController {
    private final RegistroUsuarioBonoService servicio ;

    public RegistroUsuarioBonoController(RegistroUsuarioBonoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listaRegistroBonoPorUsuario")
    public String listarRegistroBonoDeusuario(
            Model model ,
            HttpSession session
    ){
        Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        try {
            List<RegistroUsuarioBonoDTO>  listaRegistros= servicio.listarPorUsuario(usuario.getIdUsuario());
            model.addAttribute("listaRegistros", listaRegistros);
            model.addAttribute("idUsuario", usuario.getIdUsuario());
            return "ListaRegistrosBonoUsuario";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "ListaRegistrosBonoUsuario";
        }
    }

    @PostMapping("/comprarTipo1")
    public String comprarTipo1(
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        try {
            servicio.crearBonoTipo1(usuario.getIdUsuario());
            redirectAttributes.addFlashAttribute("mensaje", "Compra Exitosa");
            return "redirect:/registroClase/listaClasesUsuario";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuario";
        }
    }

    @PostMapping("/comprarTipo2")
    public String comprarTipo2(
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        try {
            servicio.crearBonoTipo2(usuario.getIdUsuario());
            redirectAttributes.addFlashAttribute("mensaje", "Compra Exitosa");
            return "redirect:/registroClase/listaClasesUsuario";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuario";
        }
    }

}
