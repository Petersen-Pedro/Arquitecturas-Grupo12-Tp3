package TP3.Repository.MySQL;

import TP3.Modelo.Estudiante;
import TP3.DTO.EstudianteDTO;
import java.util.List;

public interface EstudianteRepository {
    void addEstudiante(Estudiante estudiante);
    EstudianteDTO obtenerEstudianteLibreta(String nro_libreta_universitaria);
    List<EstudianteDTO> devolverEstudiantes();
    List<EstudianteDTO> devolverEstudiantesPorGenero(String genero);
    List<EstudianteDTO> devolverEstudiantesDeXCarreraPorCiudad(int idCarrera, String ciudad);
}
