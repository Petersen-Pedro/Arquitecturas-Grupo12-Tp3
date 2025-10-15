package TP3.DTO;

import TP3.Modelo.EstudianteCarrera;
import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarrerasDTO {
    private String carrera;
    private int duracion;
    private List<EstudianteCarrera> estudiantes;
}
