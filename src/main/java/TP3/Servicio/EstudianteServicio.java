package TP3.Servicio;

import TP3.Modelo.*;
import TP3.Repository.*;
import TP3.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {

    @Autowired
    private EstudianteRepository repoEstudiante;

    // Obtener todos los estudiantes
    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAll() throws Exception {
        try {
            List<Estudiante> estudiantes = repoEstudiante.findAll();
            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante e : estudiantes) {
                estudianteDTOS.add(this.toDTO(e));
            }
            return estudianteDTOS;
        } catch (Exception e) {
            throw new Exception("Error al obtener estudiantes!" + e.getMessage());
        }
    }

    // Obtener un estudiante por ID
    @Transactional(readOnly = true)
    public EstudianteDTO findById(String id) throws Exception {
        try {
            Optional<Estudiante> estudianteBuscado = repoEstudiante.findById(id);
            EstudianteDTO estudianteDTO = this.toDTO(estudianteBuscado.get());
            return estudianteDTO;
        } catch (Exception e) {
            throw new Exception("Error al obtener estudiante con id=" + id + "!" + e.getMessage());
        }
    }

    // 2a) Dar de alta un estudiante.
    // Guardar un nuevo estudiante
    @Transactional
    public EstudianteDTO save(Estudiante estudiante) throws Exception {
        try {
            Estudiante estudianteGuardado = repoEstudiante.save(estudiante);
            EstudianteDTO estudianteDTO = this.toDTO(estudianteGuardado);
            return estudianteDTO;
        } catch (Exception e) {
            throw new Exception("Error al guardar estudiante!" + e.getMessage());
        }
    }

    // Actualizar un estudiante
    @Transactional
    public EstudianteDTO update(String id, Estudiante estudiante) throws Exception {
        try {
            if (repoEstudiante.existsById(id)){
                Estudiante estudianteGuardado = repoEstudiante.save(estudiante);
                EstudianteDTO estudianteDTO = this.toDTO(estudianteGuardado);
                return estudianteDTO;
            }
            throw new Exception("Estudiante no encontrado");
        } catch (Exception e) {
            throw new Exception("Error al actualizar estudiante con id=" + id + "!" + e.getMessage());
        }
    }

    // Eliminar un estudiante
    @Transactional
    public boolean delete(String id) throws Exception {
        try {
            if (repoEstudiante.existsById(id)) {
                repoEstudiante.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar estudiante con id=" + id + "!" + e.getMessage());
        }
    }

    // Obtener EstudianteDTO
    public EstudianteDTO toDTO(Estudiante estudiante) {
        EstudianteDTO estudianteDTO = new EstudianteDTO(
                estudiante.getNombres(),
                estudiante.getApellido(),
                estudiante.getEdad(),
                estudiante.getNro_documento(),
                estudiante.getGenero(),
                estudiante.getCiudad_residencia(),
                estudiante.getNro_libreta_universitaria()
        );

        return estudianteDTO;
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> obtenerEstudiantesOrdenadosPorApellidoASC() throws Exception {
        try {
            List<Estudiante> estudiantes = repoEstudiante.obtenerEstudiantesOrdenadosPorApellidoASC();
            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante e : estudiantes) {
                estudianteDTOS.add(this.toDTO(e));
            }
            return estudianteDTOS;
        } catch (Exception e) {
            throw new Exception("Error al obtener estudiantes!" + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public EstudianteDTO obtenerPorLu(Long lu) throws Exception {
        try{
            Estudiante estudiante = repoEstudiante.getEstudianteByLu(lu);
            EstudianteDTO estudianteDTO = this.toDTO(estudiante);
            return estudianteDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> obtenerPorGenero(String genero){
        try{
            List<Estudiante> estudiantes = repoEstudiante.obtenerPorGenero(genero);
            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante estudiante : estudiantes) {
                estudianteDTOS.add(this.toDTO(estudiante));
            }
            return estudianteDTOS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String carrera, String ciudad){
        try{
            List<Estudiante> estudiantes = repoEstudiante.getEstudiantesByCarreraAndCiudad(carrera, ciudad);

            List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
            for (Estudiante estudiante : estudiantes) {
                estudianteDTOS.add(this.toDTO(estudiante));
            }
            return estudianteDTOS;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
