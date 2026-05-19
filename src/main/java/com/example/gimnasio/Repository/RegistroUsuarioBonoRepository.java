package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.RegistroUsuarioBonoDTO;
import com.example.gimnasio.Models.RegistroUsuarioBono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroUsuarioBonoRepository extends JpaRepository<RegistroUsuarioBono, Integer> {

    @Query("""
    SELECT rub
    FROM RegistroUsuarioBono rub
    JOIN Clase c ON c.idBono.idBono = rub.tipoBono.idBono
    WHERE rub.usuario.idUsuario = :idUsuario
      AND c.idClase = :idClase
      AND rub.usos < rub.tipoBono.numeroDeUsos
    ORDER BY rub.fechaCompra ASC
    """)
    List<RegistroUsuarioBono> buscarBonosDisponiblesParaClase(int idUsuario, int idClase);


    @Query("""
    SELECT new com.example.gimnasio.DTO.RegistroUsuarioBonoDTO(
        rub.idRegistroBono ,
        rub.tipoBono.tituloBono,
        rub.fechaCompra ,
        rub.usos ,
        rub.tipoBono.numeroDeUsos
    )FROM RegistroUsuarioBono rub
    WHERE rub.usuario.idUsuario = :idUsuario
    ORDER BY rub.fechaCompra ASC
    """)
    List<RegistroUsuarioBonoDTO> buscarBonosPorUsuario(int idUsuario);
}
