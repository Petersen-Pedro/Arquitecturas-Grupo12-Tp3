package TP3.Servicio;

import TP3.Modelo.*;
import TP3.Repository.*;
import TP3.DTO.*;

import TP3.Utils.ToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {

    @Autowired
    private EstudianteRepository repoEstudiante;

    @Autowired
    private ToDTOMapper mapper;

    // Obtener todos los estudiantes
    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAll(Sort sort) {
            List<Estudiante> estudiantes = repoEstudiante.findAll(sort);
            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante e : estudiantes) {
                estudianteDTOS.add(mapper.toDTO(e));
            }
            return estudianteDTOS;
    }

    // Obtener un estudiante por ID
    @Transactional(readOnly = true)
    public EstudianteDTO findById(String id) {
            Optional<Estudiante> estudianteBuscado = repoEstudiante.findById(id);
            EstudianteDTO estudianteDTO = mapper.toDTO(estudianteBuscado.get());
            return estudianteDTO;

    }

    // 2a) Dar de alta un estudiante. Guardar un nuevo estudiante
    @Transactional
    public EstudianteDTO save(Estudiante estudiante) {
            Estudiante estudianteGuardado = repoEstudiante.save(estudiante);
            EstudianteDTO estudianteDTO = mapper.toDTO(estudianteGuardado);
            return estudianteDTO;
    }

    // Actualizar un estudiante
    @Transactional
    public EstudianteDTO update(String id, Estudiante estudiante) {
        Estudiante estudianteGuardado = repoEstudiante.save(estudiante);
        EstudianteDTO estudianteDTO = mapper.toDTO(estudianteGuardado);
        return estudianteDTO;
    }

    // Eliminar un estudiante
    @Transactional
    public boolean delete(String id) {
            if (repoEstudiante.existsById(id)) {
                repoEstudiante.deleteById(id);
                return true;
            }
            return false;
    }

    //Obtener por libreta universitaria
    @Transactional(readOnly = true)
    public EstudianteDTO obtenerPorLu(Long lu) {
            Estudiante estudiante = repoEstudiante.getEstudianteByLu(lu);
            EstudianteDTO estudianteDTO = mapper.toDTO(estudiante);
            return estudianteDTO;

    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> obtenerPorGenero(String genero){
            List<Estudiante> estudiantes = repoEstudiante.obtenerPorGenero(genero);
            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante estudiante : estudiantes) {
                estudianteDTOS.add(mapper.toDTO(estudiante));
            }
            return estudianteDTOS;
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad){
            List<Estudiante> estudiantes = repoEstudiante.getEstudiantesByCarreraAndCiudad(carrera, ciudad);

            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante estudiante : estudiantes) {
                estudianteDTOS.add(mapper.toDTO(estudiante));
            }
            return estudianteDTOS;

    }
}
