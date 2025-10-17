package TP3.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteCarreraDTO {

    private EstudianteDTO estudiante;
    private String carrera;
    private Integer inscripcion;
    private Integer graduacion;
    private Integer antiguedad;
}


