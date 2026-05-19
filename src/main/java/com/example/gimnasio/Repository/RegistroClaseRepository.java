package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.Models.RegistroClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegistroClaseRepository extends JpaRepository<RegistroClase, Integer> {

    @Query("""
    SELECT new com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO(
        RC.idRegistroClase,
        C.titulo,
        C.fecha ,
        C.hora,
        RC.estado
    )
    FROM RegistroClase RC join Clase C on RC.clase.idClase = C.idClase
        join RegistroUsuarioBono RB on RB.idRegistroBono = RC.registroUsuarioBono.idRegistroBono
        WHERE RB.usuario.idUsuario = :idUsuario """)
    List<ListaRegistroClaseUsuarioDTO> bucarClasesPorUsuario(int idUsuario);

    boolean existsByClase_IdClaseAndRegistroUsuarioBono_Usuario_IdUsuario(int idClase, int idUsuario);

    List<RegistroClase> findByClaseIdClase(int idClase);
    Optional<RegistroClase> findByClaseIdClaseAndRegistroUsuarioBonoIdRegistroBono(int idClase, int idRegistroBono);

}