package TP3.Repository;

import TP3.Modelo.*;
import TP3.DTO.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("CarrerasRepository")
public interface CarrerasRepository extends JpaRepository<Carreras, Integer> {

    // 2f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    @Query("SELECT new TP3.DTO.InscriptosContadorDTO(" +
            " c.carrera, COUNT(ce)) " +
            "FROM Carreras c " +
            "JOIN c.estudiantes ce " +
            "WHERE ce.inscripcion > 0 " +
            "GROUP BY c.carrera " +
            "ORDER BY COUNT(ce) DESC"
    )
    //"SELECT c, COUNT(ce) FROM Carreras c JOIN c.estudiantes ce WHERE ce.inscripcion > 0 GROUP BY c ORDER BY COUNT(ce) DESC"
    List<InscriptosContadorDTO> getCarrerasOrdenadasPorInscriptos();

    // 2h) Generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    // presentar los años de manera cronológica.
    @Query("SELECT new TP3.DTO.ReporteCarreraDTO(" +
            "c.carrera, " +
            "ec.inscripcion, " +
            "ec.graduacion, " +
            "COUNT(ec), " + // Inscripciones sin egreso
            "SUM(CASE WHEN ec.graduacion IS NOT NULL THEN 1 ELSE 0 END)) " + // Inscripciones con egreso
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c " +
            "WHERE ec.graduacion IS NULL OR ec.graduacion = 0 OR ec.inscripcion <= ec.graduacion " +
            "GROUP BY c.carrera, ec.inscripcion, ec.graduacion " +
            "ORDER BY c.carrera, ec.inscripcion, ec.graduacion")
    List<ReporteCarreraDTO> getReporteCarreras();


}

