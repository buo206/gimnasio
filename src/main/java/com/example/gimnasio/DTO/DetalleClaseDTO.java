package com.example.gimnasio.DTO;

import com.example.gimnasio.Models.Estado;

public record DetalleClaseDTO(
        int idClase,
        String nombre,
        String apellidos,
        Estado estado,
        int idBono,
        String tipoBono
) {

}
