package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.Models.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Integer> {
    @Query("""
 SELECT new com.example.gimnasio.DTO.ListaClaseEntrenadorDTO(
    c.idClase, c.titulo, c.fecha, c.hora, c.cupoMax) 
 FROM Clase c 
 WHERE c.entrenador.idEntrenador = :id
""")
    List<ListaClaseEntrenadorDTO> obtenerlista(@Param("id") Integer id);

    @Query("""
 SELECT DISTINCT new com.example.gimnasio.DTO.ListaClaseEntrenadorDTO(
    c.idClase, c.titulo, c.fecha, c.hora, c.cupoMax)
 FROM Clase c
 JOIN RegistroUsuarioBono rub ON rub.tipoBono.idBono = c.idBono.idBono
 WHERE c.entrenador.idEntrenador = :idEntrenador
   AND rub.usuario.idUsuario = :idUsuario
   AND rub.usos < rub.tipoBono.numeroDeUsos
   AND NOT EXISTS (
      SELECT rc.idRegistroClase
      FROM RegistroClase rc
      WHERE rc.clase.idClase = c.idClase
        AND rc.registroUsuarioBono.usuario.idUsuario = :idUsuario
   )
""")
    List<ListaClaseEntrenadorDTO> obtenerClasesDisponiblesPorEntrenadorYUsuario(
            @Param("idEntrenador") Integer idEntrenador,
            @Param("idUsuario") Integer idUsuario
    );
}
