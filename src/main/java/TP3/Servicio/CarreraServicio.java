package TP3.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import TP3.DTO.*;
import TP3.Repository.*;
import TP3.Modelo.*;
import java.util.List;
import java.util.Optional;

@Service
public class CarreraServicio {
    @Autowired
    private CarrerasRepository repoCarrera;

    // Obtener todas las carreras
    @Transactional(readOnly = true)
    public List<CarrerasDTO> findAll() throws Exception {
        try {
            List<Carreras> carreras = repoCarrera.findAll();
            List<CarrerasDTO> carrerasDTOS = new ArrayList<>();
            for (Carreras c : carreras) {
                carrerasDTOS.add(this.toDTO(c));
            }
            return carrerasDTOS;
        } catch (Exception e) {
            throw new Exception("Error al obtener carreras!" + e.getMessage());
        }
    }

    // Obtener una carrera por ID
    @Transactional(readOnly = true)
    public CarrerasDTO findById(int id) throws Exception {
        try {
            Optional<Carreras> carreraBuscada = repoCarrera.findById(id);
            CarrerasDTO carreraDTO = this.toDTO(carreraBuscada.get());
            return carreraDTO;
        } catch (Exception e) {
            throw new Exception("Error al obtener carrera con id=" + id + "!" + e.getMessage());
        }
    }

    // Guardar una nueva carrera
    @Transactional
    public CarrerasDTO save(Carreras carrera) throws Exception { //Tiene que devolver un DTO? Al igual que update
        try {
            repoCarrera.save(carrera);
            CarrerasDTO carreraDTO = this.toDTO(carrera);
            return carreraDTO;
        } catch (Exception e) {
            throw new Exception("Error al guardar carrera!" + e.getMessage());
        }
    }

    // Actualizar una carrera
    @Transactional
    public CarrerasDTO update(int id, Carreras carrera) throws Exception {
        return null;
    }
    // Eliminar una carrera
    @Transactional
    public boolean delete(int id) throws Exception {
        try {
            if (repoCarrera.existsById(id)) {
                repoCarrera.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar carrera con id=" + id + "!" + e.getMessage());
        }
    }

    // Obtener CarreraDTO
    public CarrerasDTO toDTO(Carreras carrera) {
        return new CarrerasDTO(carrera.getCarrera(), carrera.getDuracion(), carrera.getEstudiantes());
    }

    @Transactional(readOnly = true)
    public List<ReporteCarreraDTO> generarReporteCarreras(){
        return repoCarrera.getReporteCarreras();
    }

    @Transactional(readOnly = true)
    public List<InscriptosContadorDTO> getCarrerasOrdenadasPorInscriptos(){
        return repoCarrera.getCarrerasOrdenadasPorInscriptos();
    }
}
