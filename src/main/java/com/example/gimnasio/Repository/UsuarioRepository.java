package com.example.gimnasio.Repository;

import com.example.gimnasio.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
