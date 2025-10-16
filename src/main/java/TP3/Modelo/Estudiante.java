package TP3.Modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Table(name = "estudiante")
@Entity
public class Estudiante {

    @Id
//  @Setter(AccessLevel.NONE)
    private String nro_documento;

    @Column
    private String nombres;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private String ciudad_residencia;

    @Column
    private String nro_libreta_universitaria;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Carreras> carreras;


}

/*  public void addInscripcion(EstudianteCarrera estudianteCarrera) {
        if (!inscripciones.contains(estudianteCarrera)) {
            inscripciones.add(estudianteCarrera);
            estudianteCarrera.setEstudiante(this); // Mantener la relación bidireccional
        }
    }

    public void removeInscripcion(EstudianteCarrera estudianteCarrera) {
        if (inscripciones.contains(estudianteCarrera)) {
            inscripciones.remove(estudianteCarrera);
            estudianteCarrera.setEstudiante(null); // Mantener la relación bidireccional
        }
    }
}*/