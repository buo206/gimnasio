package com.example.gimnasio.Controller;

import com.example.gimnasio.DTO.LoguinDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
    @GetMapping("")
    public String iniciar(Model model){
        return "redirect:/entrenador" ;
    }
}
