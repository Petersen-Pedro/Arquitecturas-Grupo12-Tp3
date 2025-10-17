package TP3.Repository;

import TP3.Modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("EstudianteCarreraRepository")
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Integer> {

}