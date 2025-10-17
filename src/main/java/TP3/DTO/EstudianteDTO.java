package TP3.DTO;

import TP3.Modelo.EstudianteCarrera;
import lombok.*;
import java.util.List;


@Data
@NoArgsConstructor

public class EstudianteDTO {
    private String nro_documento;
    private String nombres;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad_residencia;
    private String nro_libreta_universitaria;
/*
*/
    public EstudianteDTO(String nombres, String apellido, int edad, String nroDocumento, String genero, String ciudadResidencia, String nroLibretaUniversitaria) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad_residencia = ciudadResidencia;
        this.nro_libreta_universitaria = nroLibretaUniversitaria;
        this.nro_documento = nroDocumento;
    }

    @Override
    public String toString(){
        return String.format("Nombre completo: " + nombres + ", " + apellido + ". Edad: " + edad);
    }

    //@Getter(AccessLevel.NONE)
    //@Setter(AccessLevel.NONE)
    private List<EstudianteCarrera> inscripciones;

    public void addInscripcion(EstudianteCarrera inscripcion) {
        if (!inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
        }
    }

    public void removeInscripcion(EstudianteCarrera inscripcion) {
        inscripciones.remove(inscripcion);
    }
}



