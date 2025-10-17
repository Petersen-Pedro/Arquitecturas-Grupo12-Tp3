package TP3.Servicio;

import TP3.Utils.ToDTOMapper;
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
    @Autowired
    private ToDTOMapper mapper;

    //Obtener todas las carreras
    @Transactional(readOnly = true)
    public List<CarrerasDTO> findAll() {
            List<Carreras> carreras = repoCarrera.findAll();
            List<CarrerasDTO> carrerasDTOS = new ArrayList<>();
            for (Carreras c : carreras) {
                carrerasDTOS.add(this.mapper.toDTO(c));
            }
            return carrerasDTOS;
    }

    //Obtener una carrera por ID
    @Transactional(readOnly = true)
    public CarrerasDTO findById(int id) {
            Optional<Carreras> carreraBuscada = repoCarrera.findById(id);
            CarrerasDTO carreraDTO = this.mapper.toDTO(carreraBuscada.get());
            return carreraDTO;
    }

    //Guardar una nueva carrera
    @Transactional
    public CarrerasDTO save(Carreras carrera){
            repoCarrera.save(carrera);
            CarrerasDTO carreraDTO = this.mapper.toDTO(carrera);
            return carreraDTO;
    }

    //Actualizar una carrera
    @Transactional
    public CarrerasDTO update(int id, Carreras carrera){
        return null;
    }
    //Eliminar una carrera
    @Transactional
    public boolean delete(int id) {
            if (repoCarrera.existsById(id)) {
                repoCarrera.deleteById(id);
                return true;
            }
            return false;
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
