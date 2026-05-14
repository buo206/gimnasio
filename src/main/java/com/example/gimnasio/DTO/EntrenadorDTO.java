package com.example.gimnasio.DTO;

public record EntrenadorDTO(
        int idEntrenador ,
        String nombre ,
        String apellidos ,
        String direccion ,
        String email ,
        String password ,
        String telefono
) {
}
