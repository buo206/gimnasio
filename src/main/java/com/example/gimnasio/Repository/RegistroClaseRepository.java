package com.example.gimnasio.Repository;

import com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO;
import com.example.gimnasio.Models.RegistroClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroClaseRepository extends JpaRepository<RegistroClase, Integer> {

    @Query("""
    SELECT new com.example.gimnasio.DTO.ListaRegistroClaseUsuarioDTO(
        C.idClase,
        C.titulo,
        RC.estado
    )
    FROM RegistroClase RC join Clase C on RC.clase.idClase = C.idClase
        join RegistroUsuarioBono RB on RB.idRegistroBono = RC.registroUsuarioBono.idRegistroBono
        WHERE RB.usuario.idUsuario = :idUsuario """)
    List<ListaRegistroClaseUsuarioDTO> bucarClasesPorUsuario(int idUsuario);


}