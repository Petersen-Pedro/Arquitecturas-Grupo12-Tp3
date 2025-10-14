package TP3.Repository.MySQL;


import TP3.Modelo.Carreras;
import java.util.List;

public interface CarrerasRepository {
    void generarReporte();
    Carreras devolverCarreraPorId(Long id);
    List<Object[]> carrerasConEstudiantes();
}
