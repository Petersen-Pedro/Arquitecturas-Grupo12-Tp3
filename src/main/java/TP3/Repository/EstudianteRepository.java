package TP3.Repository;

import TP3.Modelo.*;
import TP3.DTO.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("EstudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    // 2c) Punto 2.c devolver todos los estudiantes ordenados por un criterio
    @Query("SELECT new TP3.DTO.EstudianteDTO(e.nombres, e.apellido, e.edad, e.genero, e.nro_documento, e.ciudad_residencia, e.nro_libreta_universitaria) " +
            "FROM Estudiante e ORDER BY e.edad DESC")
    List<Estudiante> obtenerEstudiantesOrdenadosPorApellidoASC();

    // 2d) Eecuperar un estudiante, en base a su número de libreta universitaria.
    @Query("SELECT e FROM Estudiante e WHERE e.nro_libreta_universitaria = :lu")
    Estudiante getEstudianteByLu(Long lu);

    // 2e) Recuperar todos los estudiantes, en base a su género.
    @Query( "SELECT new TP3.DTO.EstudianteDTO(e.nombres, e.apellido, e.edad, e.nro_documento, e.genero, e.ciudad_residencia, e.nro_libreta_universitaria) " +
            "FROM Estudiante e " +
            "WHERE e.genero = :genero " +
            "ORDER BY e.edad DESC")
    List<Estudiante> obtenerPorGenero(String genero);

    // 2g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Query( "SELECT new TP3.DTO.EstudianteDTO(e.nombres, e.apellido, e.edad, e.nro_documento, e.genero, e.ciudad_residencia, e.nro_libreta_universitaria) " +
            "FROM Estudiante e " +
            "JOIN EstudianteCarrera ec ON ec.estudiante = e " +
            "JOIN Carreras c ON ec.carrera = c " +
            "WHERE c.id_carrera = :idCarrera AND e.ciudad_residencia = :ciudad")
    List<Estudiante> getEstudiantesByCarreraAndCiudad(String idCarrera, String ciudad);

    /*
        void addEstudiante(Estudiante estudiante);
        EstudianteDTO obtenerEstudianteLibreta(String nro_libreta_universitaria);
        List<EstudianteDTO> devolverEstudiantes();
        List<EstudianteDTO> devolverEstudiantesPorGenero(String genero);
        List<EstudianteDTO> devolverEstudiantesDeXCarreraPorCiudad(int idCarrera, String ciudad);
    */
}