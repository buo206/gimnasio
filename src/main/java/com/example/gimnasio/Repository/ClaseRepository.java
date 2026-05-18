package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.DetalleClaseDTO;
import com.example.gimnasio.DTO.ListaClaseEntrenadorDTO;
import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
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
    List<ListaClaseEntrenadorDTO> obtenerlista(Integer id);

    @Query("""
    SELECT new com.example.gimnasio.DTO.DetalleClaseDTO(
        c.idClase,u.nombre,u.apellidos,rc.estado,tb.idBono,tb.tituloBono)
    FROM RegistroClase rc JOIN Clase c ON rc.clase = c
    JOIN RegistroUsuarioBono rb ON rc.registroUsuarioBono = rb
    JOIN TipoBono tb ON rb.tipoBono = tb
    JOIN Usuario u ON rb.usuario = u
    WHERE c.idClase = :idClase
    """)
    List<DetalleClaseDTO> obtenerDetalleClase(int idClase);

    @Query("""
 SELECT DISTINCT new com.example.gimnasio.DTO.ListaClaseEntrenadorDTO(
    c.idClase, c.titulo, c.fecha, c.hora, c.cupoMax)
 FROM Clase c
 JOIN RegistroUsuarioBono rub ON rub.tipoBono.idBono = c.idBono.idBono
 WHERE rub.usuario.idUsuario = :idUsuario
   AND rub.usos < rub.tipoBono.numeroDeUsos
   AND NOT EXISTS (
      SELECT rc.idRegistroClase
      FROM RegistroClase rc
      WHERE rc.clase.idClase = c.idClase
        AND rc.registroUsuarioBono.usuario.idUsuario = :idUsuario
   )
""")
    List<ListaClaseEntrenadorDTO> obtenerClasesDisponiblesPorEntrenadorYUsuario(int idUsuario);
}

