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
    @Setter(AccessLevel.NONE)
    private List<EstudianteCarrera> estudiantes;


    public void addInscripcion(EstudianteCarrera estudianteCarrera) {
        if (!estudiantes.contains(estudianteCarrera)) {
            estudiantes.add(estudianteCarrera);
            estudianteCarrera.setCarrera(this); // Mantener la relación bidireccional
        }
    }

    public void removeInscripcion(EstudianteCarrera estudianteCarrera) {
        if (estudiantes.contains(estudianteCarrera)) {
            estudiantes.remove(estudianteCarrera);
            estudianteCarrera.setCarrera(null); // Mantener la relación bidireccional
        }
    }
}