package TP3.Modelo;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "estudianteCarrera")
@Entity
public class EstudianteCarrera {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nro_documento")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carreras carrera;

    @Column
    private Integer inscripcion;

    @Column
    private Integer graduacion;

    @Column
    private Integer antiguedad;

}