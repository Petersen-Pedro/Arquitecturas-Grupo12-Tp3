package TP3.Modelo;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Table(name = "estudiante")
@Entity
public class Estudiante {

    @Id
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
    private List<EstudianteCarrera> carreras;

    public void addInscripcion(EstudianteCarrera estudianteCarrera) {
        if (!carreras.contains(estudianteCarrera)) {
            carreras.add(estudianteCarrera);
            estudianteCarrera.setEstudiante(this); //Mantener la relación bidireccional
        }
    }

    public void removeInscripcion(EstudianteCarrera estudianteCarrera) {
        if (carreras.contains(estudianteCarrera)) {
            carreras.remove(estudianteCarrera);
            estudianteCarrera.setEstudiante(null); //Mantener la relación bidireccional
        }
    }

    public void setNro_documento(String nroDocumento) {
        if (this.nro_documento == null) {
            this.nro_documento = nroDocumento;
        } else {
            throw new IllegalStateException("nro_documento can only be set once");
        }
    }
}

