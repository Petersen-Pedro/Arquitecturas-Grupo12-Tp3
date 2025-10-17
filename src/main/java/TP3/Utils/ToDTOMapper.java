package TP3.Utils;

import TP3.DTO.CarrerasDTO;
import TP3.DTO.EstudianteDTO;
import TP3.Modelo.Carreras;
import TP3.Modelo.Estudiante;
import TP3.Modelo.EstudianteCarrera;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDTOMapper {
    public EstudianteDTO toDTO(Estudiante estudiante) {
        return new EstudianteDTO(
                estudiante.getNombres(),
                estudiante.getApellido(),
                estudiante.getEdad(),
                estudiante.getNro_documento(),
                estudiante.getGenero(),
                estudiante.getCiudad_residencia(),
                estudiante.getNro_libreta_universitaria()
        );
    }

    public CarrerasDTO toDTO(Carreras carrera) {
        List<EstudianteDTO> estudiantes = carrera.getEstudiantes().stream()
                .map(EstudianteCarrera::getEstudiante)
                .map(this::toDTO)
                .toList();

        return new CarrerasDTO(carrera.getCarrera(), carrera.getDuracion(), estudiantes);
    }

}
