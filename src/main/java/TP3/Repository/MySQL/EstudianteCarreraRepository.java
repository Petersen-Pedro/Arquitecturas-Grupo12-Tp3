package TP3.Repository.MySQL;

import TP3.Modelo.Carreras;
import TP3.Modelo.Estudiante;

public interface EstudianteCarreraRepository {
    void matricularEstudiante(Long id, Estudiante estudiante, Carreras carrera, int inscripcion, int graduacion, int antiguedad);
}
