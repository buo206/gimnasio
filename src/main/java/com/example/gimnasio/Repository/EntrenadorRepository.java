package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.EntrenadorDTO;
import com.example.gimnasio.Models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
    Optional<EntrenadorDTO> findByEmailAndPassword(String email , String password);
}
