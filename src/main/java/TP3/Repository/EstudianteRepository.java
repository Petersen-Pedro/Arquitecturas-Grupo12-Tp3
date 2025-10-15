package TP3.Repository;

import TP3.Modelo.*;
import TP3.DTO.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("EstudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    // 2c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple. -> Por APELLIDO
    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    List<Estudiante> obtenerEstudiantesOrdenadosPorApellidoASC();

    // 2d) Eecuperar un estudiante, en base a su número de libreta universitaria.
    @Query("SELECT e FROM Estudiante e WHERE e.lu = :lu")
    Estudiante getEstudianteByLu(Long lu);

    // 2e) Recuperar todos los estudiantes, en base a su género.
    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero")
    List<Estudiante> obtenerPorGenero(String genero);

    // 2g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Query("SELECT e " +
            "FROM Estudiante e " +
            "JOIN e.inscripciones i " +
            "JOIN i.carrera c " +
            "WHERE e.ciudadResidencia LIKE :ciudadResidencia " +
            "AND c.nombre LIKE :nombreCarrera")
    List<Estudiante> getEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudadResidencia);

    /*
        void addEstudiante(Estudiante estudiante);
        EstudianteDTO obtenerEstudianteLibreta(String nro_libreta_universitaria);
        List<EstudianteDTO> devolverEstudiantes();
        List<EstudianteDTO> devolverEstudiantesPorGenero(String genero);
        List<EstudianteDTO> devolverEstudiantesDeXCarreraPorCiudad(int idCarrera, String ciudad);
    */
}