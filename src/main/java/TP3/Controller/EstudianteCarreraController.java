package TP3.Controller;

import TP3.DTO.CarrerasDTO;
import TP3.DTO.EstudianteCarreraDTO;
import TP3.DTO.ReporteCarreraDTO;
import TP3.Modelo.Carreras;
import TP3.Modelo.Estudiante;
import TP3.Modelo.EstudianteCarrera;
import TP3.Repository.CarrerasRepository;
import TP3.Servicio.CarreraServicio;

import TP3.Servicio.EstudianteCarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/inscripciones")
public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraServicio matriculacionServicio;

    // 2b) Matricular un estudiante en una carrera
    public ResponseEntity<?> matricularEstudiante(@RequestBody Map<String, Object> request) {
        try {
            Map<String, Object> estudianteMap = (Map<String, Object>) request.get("estudiante");
            Map<String, Object> carreraMap = (Map<String, Object>) request.get("carrera");

            Estudiante estudiante = new Estudiante();
            estudiante.setNro_documento(String.valueOf(estudianteMap.get("dni")));
            estudiante.setNombres((String) estudianteMap.get("nombre"));
            estudiante.setApellido((String) estudianteMap.get("apellido"));
            estudiante.setEdad((Integer) estudianteMap.get("edad"));
            estudiante.setGenero((String) estudianteMap.get("genero"));
            estudiante.setCiudad_residencia((String) estudianteMap.get("ciudadResidencia"));
            estudiante.setNro_libreta_universitaria(String.valueOf(estudianteMap.get("lu")));

            Carreras carrera = new Carreras();
            carrera.setId_carrera(Integer.parseInt(String.valueOf(carreraMap.get("id"))));
            carrera.setCarrera(String.valueOf(carreraMap.get("carrera")));
            carrera.setDuracion(Integer.parseInt(String.valueOf(carreraMap.get("duracion"))));

            EstudianteCarrera entity = new EstudianteCarrera(1L, estudiante, carrera, Year.now().getValue(), 0, 0);
            EstudianteCarreraDTO estudianteCarreraDTO = matriculacionServicio.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCarreraDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Obtener todas las inscripciones
    @GetMapping("")
    public ResponseEntity<?> obtenerTodasInscripciones() {
        try {
            List<EstudianteCarreraDTO> inscripciones = matriculacionServicio.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(inscripciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al obtener inscripciones: " + e.getMessage() + "\"}");
        }
    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInscripcionPorId(@PathVariable int id) {
        try {
            EstudianteCarreraDTO inscripcion = matriculacionServicio.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(inscripcion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}