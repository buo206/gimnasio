package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.LoguinDTO;
import com.example.gimnasio.Service.EntrenadorSevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {
    private final EntrenadorSevice servicio ;

    public EntrenadorController(EntrenadorSevice servicio) {
        this.servicio = servicio;
    }

    @GetMapping("logueo")
    public String logueo(@ModelAttribute LoguinDTO loguin){
        servicio.logueo(loguin) ;
        return "redirect:listar/"+2;
    }
}
