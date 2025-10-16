package TP3.Modelo;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Table(name = "carrera")
@Entity

public class Carreras {

    @Id
    //@Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera;

    @Column
    private String carrera;

    @Column
    private int duracion;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<EstudianteCarrera> estudiantes;


    /*
    public void addInscripcion(EstudianteCarrera estudianteCarrera) {
        if (!inscripciones.contains(estudianteCarrera)) {
            inscripciones.add(estudianteCarrera);
            estudianteCarrera.setCarrera(this); // Mantener la relación bidireccional
        }
    }

    public void removeInscripcion(EstudianteCarrera estudianteCarrera) {
        if (inscripciones.contains(estudianteCarrera)) {
            inscripciones.remove(estudianteCarrera);
            estudianteCarrera.setCarrera(null); // Mantener la relación bidireccional
        }
    }*/
}