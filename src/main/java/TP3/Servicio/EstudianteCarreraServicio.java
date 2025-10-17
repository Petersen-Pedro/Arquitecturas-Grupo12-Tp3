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

@Service("MatriculacionServicio")
public class EstudianteCarreraServicio {
    @Autowired
    private EstudianteRepository EstudianteRepo;

    @Autowired
    private CarrerasRepository CarreraRepo;

    @Autowired
    private EstudianteCarreraRepository EstudianteCarreraRepo;

    // Obtener todas las inscripciones
    @Transactional(readOnly = true)
    public List<EstudianteCarreraDTO> findAll() throws Exception {
        try {
            List<EstudianteCarrera> inscripciones = EstudianteCarreraRepo.findAll();
            List<EstudianteCarreraDTO> estudianteCarreraDTOS = new ArrayList<>();

            for (EstudianteCarrera c : inscripciones) {
                estudianteCarreraDTOS.add(this.toDTO(c));
            }
            return estudianteCarreraDTOS;
        } catch (Exception e) {
            throw new Exception("Error al obtener inscripciones!" + e.getMessage());
        }
    }

    // Obtener una inscripción por ID
    @Transactional(readOnly = true)
    public EstudianteCarreraDTO findById(int id) throws Exception {
        try {
            Optional<EstudianteCarrera> inscripcionBuscada = EstudianteCarreraRepo.findById(id);
            EstudianteCarreraDTO estudianteCarreraDTO = this.toDTO(inscripcionBuscada.get());
            return estudianteCarreraDTO;
        } catch (Exception e) {
            throw new Exception("Error al buscar inscripción  con id=" + id + "!" + e.getMessage());
        }
    }

    @Transactional
    public EstudianteCarreraDTO save(EstudianteCarrera estudianteCarrera) throws Exception {
        try {
            EstudianteCarrera estudianteGuardado = EstudianteCarreraRepo.save(estudianteCarrera);
            EstudianteCarreraDTO estudianteCarreraDTO = this.toDTO(estudianteGuardado);
            return estudianteCarreraDTO;
        } catch (Exception e) {
            throw new Exception("Error al matricular estudiante!" + e.getMessage());
        }
    }

    // Actualizar una inscripción
    @Transactional
    public EstudianteCarreraDTO update(int id, EstudianteCarrera estudianteCarrera) throws Exception {
        try {
            // Buscar la inscripción existente por ID
            EstudianteCarrera inscripcion = EstudianteCarreraRepo.findById(id)
                    .orElseThrow(() -> new Exception("Inscripción no encontrada con id=" + id + "!"));

            // Buscar estudiante existente
            String idEstudiante = estudianteCarrera.getEstudiante().getNro_documento();
            Estudiante estudiante = EstudianteRepo.findById(idEstudiante)
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id=" + idEstudiante + "!"));

            // Buscar carrera existente
            int idCarrera = estudianteCarrera.getCarrera().getId_carrera();
            Carreras carrera = CarreraRepo.findById(idCarrera)
                    .orElseThrow(() -> new RuntimeException("Carrera no encontrada con id=" + idCarrera + "!"));

            // Actualizar campos de inscripcion
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCarrera(carrera);
            inscripcion.setInscripcion(estudianteCarrera.getInscripcion());
            inscripcion.setGraduacion(estudianteCarrera.getGraduacion());
            inscripcion.setAntiguedad(estudianteCarrera.getAntiguedad());

            // Guardar la inscripción actualizada en la base de datos
            EstudianteCarreraRepo.save(inscripcion);

            //Convierte la inscripcion en DTO
            EstudianteCarreraDTO estudianteCarreraDTO = this.toDTO(inscripcion);
            return estudianteCarreraDTO;
        } catch (Exception e) {
            throw new Exception("Error al actualizar inscripción con id=" + id + "!" + e.getMessage());
        }
    }

    // Eliminar una inscripción
    @Transactional
    public boolean delete(int id) throws Exception {
        try {
            if (EstudianteCarreraRepo.existsById(id)) {
                EstudianteCarreraRepo.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar inscripción  con id=" + id + "!" + e.getMessage());
        }
    }

    // Obtener EstudianteCarreraDTO
    public EstudianteCarreraDTO toDTO(EstudianteCarrera estudianteCarrera) {
        EstudianteCarreraDTO estudianteCarreraDTO = new EstudianteCarreraDTO(
                estudianteCarrera.getEstudiante(),
                estudianteCarrera.getCarrera(),
                estudianteCarrera.getInscripcion(),
                estudianteCarrera.getGraduacion(),
                estudianteCarrera.getAntiguedad()
        );

        return estudianteCarreraDTO;
    }
}
