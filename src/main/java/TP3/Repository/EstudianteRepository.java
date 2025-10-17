package TP3.Repository;

import TP3.Modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("EstudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    // 2d) Eecuperar un estudiante, en base a su número de libreta universitaria.
    @Query("SELECT e FROM Estudiante e WHERE e.nro_libreta_universitaria = :lu")
    Estudiante getEstudianteByLu(Long lu);

    // 2e) Recuperar todos los estudiantes, en base a su género.
    @Query( "SELECT e " +
            "FROM Estudiante e " +
            "WHERE e.genero = :genero ")
    List<Estudiante> obtenerPorGenero(@Param("genero") String genero);

    // 2g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Query( "SELECT e " +
            "FROM Estudiante e " +
            "JOIN EstudianteCarrera ec ON ec.estudiante = e " +
            "JOIN Carreras c ON ec.carrera = c " +
            "WHERE c.id_carrera = :idCarrera AND e.ciudad_residencia = :ciudad")
    List<Estudiante> getEstudiantesByCarreraAndCiudad(String idCarrera, String ciudad);
}