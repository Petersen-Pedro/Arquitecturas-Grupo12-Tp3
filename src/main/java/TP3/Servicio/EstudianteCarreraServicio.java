package TP3.Servicio;

import TP3.Modelo.*;
import TP3.Repository.*;
import TP3.DTO.*;

import TP3.Utils.ToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("MatriculacionServicio")
public class EstudianteCarreraServicio {
    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private CarrerasRepository carreraRepo;

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepo;

    @Autowired
    private ToDTOMapper mapper;

    @Transactional(readOnly = true)
    public List<EstudianteCarreraDTO> findAll(){

            List<EstudianteCarrera> inscripciones = estudianteCarreraRepo.findAll();
            List<EstudianteCarreraDTO> estudianteCarreraDTOS = new ArrayList<>();

            for (EstudianteCarrera c : inscripciones) {
                estudianteCarreraDTOS.add(this.toDTO(c));
            }
            return estudianteCarreraDTOS;
    }

    @Transactional(readOnly = true)
    public EstudianteCarreraDTO findById(int id){
            Optional<EstudianteCarrera> inscripcionBuscada = estudianteCarreraRepo.findById(id);
            EstudianteCarreraDTO estudianteCarreraDTO = this.toDTO(inscripcionBuscada.get());
            return estudianteCarreraDTO;
    }

    @Transactional
    public EstudianteCarreraDTO save(MatricularEstudianteDTO matricularEstudiante){
        Estudiante estudiante = estudianteRepo.findById(matricularEstudiante.getNro_documento()).get();
        Carreras carrera = carreraRepo.findById(matricularEstudiante.getId_carrera()).get();

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(
                null,
                estudiante,
                carrera,
                Year.now().getValue(),
                0,
                0
        );
            EstudianteCarrera estudianteGuardado = estudianteCarreraRepo.save(estudianteCarrera);
            EstudianteCarreraDTO estudianteCarreraDTO = this.toDTO(estudianteGuardado);
            return estudianteCarreraDTO;
    }

    // Actualizar una inscripción
    @Transactional
    public EstudianteCarreraDTO update(int id, EstudianteCarrera estudianteCarrera){
            EstudianteCarrera inscripcion = estudianteCarreraRepo.findById(id).get();

            String idEstudiante = estudianteCarrera.getEstudiante().getNro_documento();
            Estudiante estudiante = estudianteRepo.findById(idEstudiante).get();

            int idCarrera = estudianteCarrera.getCarrera().getId_carrera();
            Carreras carrera = carreraRepo.findById(idCarrera)
                    .orElseThrow(() -> new RuntimeException("Carrera no encontrada con id=" + idCarrera + "!"));

            inscripcion.setEstudiante(estudiante);
            inscripcion.setCarrera(carrera);
            inscripcion.setInscripcion(estudianteCarrera.getInscripcion());
            inscripcion.setGraduacion(estudianteCarrera.getGraduacion());
            inscripcion.setAntiguedad(estudianteCarrera.getAntiguedad());

            estudianteCarreraRepo.save(inscripcion);

            EstudianteCarreraDTO estudianteCarreraDTO = this.toDTO(inscripcion);
            return estudianteCarreraDTO;
    }

    // Eliminar una inscripción
    @Transactional
    public boolean delete(int id) {
            if (estudianteCarreraRepo.existsById(id)) {
                estudianteCarreraRepo.deleteById(id);
                return true;
            }
            return false;
    }

    // Obtener EstudianteCarreraDTO
    public EstudianteCarreraDTO toDTO(EstudianteCarrera estudianteCarrera) {
        EstudianteCarreraDTO estudianteCarreraDTO = new EstudianteCarreraDTO(
                mapper.toDTO(estudianteCarrera.getEstudiante()),
                estudianteCarrera.getCarrera().getCarrera(),
                estudianteCarrera.getInscripcion(),
                estudianteCarrera.getGraduacion(),
                estudianteCarrera.getAntiguedad()
        );

        return estudianteCarreraDTO;
    }
}
