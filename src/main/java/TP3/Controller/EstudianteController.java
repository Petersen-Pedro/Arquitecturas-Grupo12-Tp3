package TP3.Controller;

import TP3.DTO.EstudianteDTO;
import TP3.Modelo.Estudiante;
import TP3.Servicio.EstudianteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteServicio estudianteServicio;

    // 2a) Dar de alta un estudiante
    @PostMapping("/alta")
    public ResponseEntity<EstudianteDTO> altaEstudiante(@RequestBody Estudiante estudiante) {
            EstudianteDTO creado = estudianteServicio.save(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }


    // 2c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple -> Por APELLIDO
    @GetMapping("")
    public ResponseEntity<?> obtenerTodos(@SortDefault(sort = "nro_documento", direction = Sort.Direction.ASC) Sort sort) {
        List<EstudianteDTO> estudiantes = estudianteServicio.findAll(sort);
        return ResponseEntity.status(HttpStatus.OK).body(estudiantes);
    }

    // 2d) Eecuperar un estudiante, en base a su número de libreta universitaria
    @GetMapping("/lu/{lu}")
    public ResponseEntity<?> obtenerPorLu(@PathVariable Long lu) {
        EstudianteDTO estudiante = estudianteServicio.obtenerPorLu(lu);
        return ResponseEntity.status(HttpStatus.OK).body(estudiante);
    }

    // 2e) Recuperar todos los estudiantes, en base a su género
    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> obtenerPorGenero(@PathVariable String genero) {
        List<EstudianteDTO> estudiantes = estudianteServicio.obtenerPorGenero(genero);
        return ResponseEntity.status(HttpStatus.OK).body(estudiantes);
    }

    // 2g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
    @GetMapping("/carrera/{nombreCarrera}/ciudad/{ciudadResidencia}")
    public ResponseEntity<?> obtenerPorCarreraYCiudad(@PathVariable String nombreCarrera, @PathVariable String ciudadResidencia) {
        List<EstudianteDTO> estudiantes = estudianteServicio.getEstudiantesByCarreraAndCiudad(nombreCarrera, ciudadResidencia);
        return ResponseEntity.status(HttpStatus.OK).body(estudiantes);
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        EstudianteDTO estudiante = estudianteServicio.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(estudiante);
    }

    // Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstudiante(@PathVariable String id, @RequestBody Estudiante estudiante) {
        EstudianteDTO actualizado = estudianteServicio.update(id, estudiante);
        return ResponseEntity.status(HttpStatus.OK).body(actualizado);
    }

    // Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEstudiante(@PathVariable String id) {
        try {
            boolean eliminado = estudianteServicio.delete(id);
            if (eliminado) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\":\"Estudiante no encontrado.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}