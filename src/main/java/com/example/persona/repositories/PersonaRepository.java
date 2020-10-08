package com.example.persona.repositories;

import com.example.persona.entities.Persona;
import com.example.persona.servicies.PersonaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

    //hacemos el uso de querys para obtener el listado de personas de acuerdo si tiene el string nombre o apellido
    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);

    //boolean existsByDni(int dni);//se usa este metodo para verificar si existe una persona con ese dni

    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%" )//query con jpquery
    List<Persona> search(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIkE %:filtro%", nativeQuery = true )//query con sqlquery
    List<Persona> searchNativo(@Param("filtro") String filtro);
}
