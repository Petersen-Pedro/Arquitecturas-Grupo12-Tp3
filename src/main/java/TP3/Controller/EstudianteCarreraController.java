package TP3.Controller;


import TP3.DTO.EstudianteCarreraDTO;
import TP3.DTO.MatricularEstudianteDTO;
import TP3.Servicio.EstudianteCarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/inscripciones")
public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraServicio matriculacionServicio;

    // 2b) Matricular un estudiante en una carrera
    @PostMapping("")
    public ResponseEntity<EstudianteCarreraDTO> matricularEstudiante(@RequestBody MatricularEstudianteDTO request ) {

            EstudianteCarreraDTO estudianteCarreraDTO = matriculacionServicio.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCarreraDTO);

    }

    // Obtener todas las inscripciones
    @GetMapping("")
    public ResponseEntity<List<EstudianteCarreraDTO>> obtenerTodasInscripciones() {
            List<EstudianteCarreraDTO> inscripciones = matriculacionServicio.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(inscripciones);

    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteCarreraDTO> obtenerInscripcionPorId(@PathVariable int id) {
            EstudianteCarreraDTO inscripcion = matriculacionServicio.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(inscripcion);

    }
}