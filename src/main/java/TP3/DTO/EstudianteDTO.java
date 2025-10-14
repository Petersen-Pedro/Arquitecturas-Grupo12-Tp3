package TP3.DTO;

import TP3.Modelo.EstudianteCarrera;
import lombok.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO {
    private String nombres;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad_residencia;
    private String nro_libreta_universitaria;

    @Override
    public String toString(){
        return String.format("Nombre completo: " + nombres + ", " + apellido + ". Edad: " + edad);
    }
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<EstudianteCarrera> inscripciones;
}
/*
    public void addInscripcion(EstudianteCarrera inscripcion) {
        if (!inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
        }
    }

    public void removeInscripcion(EstudianteCarrera inscripcion) {
        inscripciones.remove(inscripcion);
    }
*/
