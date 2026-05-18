package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.Models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
    Optional<EntrenadorDTO> findByEmailAndPassword(String email , String password);
    @Query("""
    SELECT new com.example.gimnasio.DTO.EntrenadorDTO(
        e.idEntrenador,
        e.nombre,
        e.apellidos,
        e.direccion,
        e.email,
        e.password,
        e.telefono
    )
    FROM Entrenador e
""")
    List<EntrenadorDTO> findAllEntrenadores();
}
