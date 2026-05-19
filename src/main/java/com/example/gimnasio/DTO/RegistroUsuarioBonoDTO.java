package com.example.gimnasio.DTO;

import java.time.LocalDate;

public record RegistroUsuarioBonoDTO(
        int idRegistroBono ,
        String tituloBono  ,
        LocalDate fechaCompra,
        int usos ,
        int maxUsos
) {
}
