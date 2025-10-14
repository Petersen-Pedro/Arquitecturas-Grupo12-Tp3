package TP3.DTO;

import TP3.Modelo.Carreras;
import TP3.Modelo.Estudiante;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteCarreraDTO {

    private Estudiante estudiante;
    private Carreras carrera;
    private Integer inscripcion;
    private Integer graduacion;
    private Integer antiguedad;
}