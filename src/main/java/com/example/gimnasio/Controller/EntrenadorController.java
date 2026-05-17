package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Service.EntrenadorSevice;
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
    private final EntrenadorSevice servicio ;

    public EntrenadorController(EntrenadorSevice servicio) {
        this.servicio = servicio;
    }

    @GetMapping("")
    public String iniciar(Model model ,HttpSession session){
        EntrenadorDTO entrenador = (EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            model.addAttribute("loguin", new LoguinDTO("","") );
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
        EntrenadorDTO entrenador =(EntrenadorDTO) session.getAttribute("entrenadorLogueado");

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
        EntrenadorDTO entrenador =(EntrenadorDTO) session.getAttribute("entrenadorLogueado");

        if (entrenador == null) {
            return "redirect:/entrenador";
        }

        model.addAttribute("entrenador", entrenador);
        return "redirect:/clase/listaClases";
    }
}
