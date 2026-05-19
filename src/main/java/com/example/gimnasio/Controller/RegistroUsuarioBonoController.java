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
            Model model ,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        try {
            servicio.crearBonoTipo1(usuario.getIdUsuario());
            model.addAttribute("mensaje", "Compra Exitosa");
            return "ComprarBonos";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "ComprarBonos";
        }
    }

    @PostMapping("/comprarTipo2")
    public String comprarTipo2(
            Model model ,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/usuario";
        }

        try {
            servicio.crearBonoTipo2(usuario.getIdUsuario());
            model.addAttribute("mensaje", "Compra Exitosa");
            return "ComprarBonos";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "ComprarBonos";
        }
    }

}
