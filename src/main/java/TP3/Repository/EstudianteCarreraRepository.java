package TP3.Repository;

import TP3.Modelo.*;
import TP3.DTO.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository("EstudianteCarreraRepository")
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Integer> {

}