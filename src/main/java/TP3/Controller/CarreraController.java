package TP3.Controller;

import TP3.DTO.*;
import TP3.Modelo.*;
import TP3.Servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraServicio carreraServicio;

    //Crear una nueva carrera
    @PostMapping("/alta")
    public ResponseEntity<CarrerasDTO> crearCarrera(@RequestBody Carreras carrera) {
            CarrerasDTO carreraCreada = carreraServicio.save(carrera);
            return ResponseEntity.status(HttpStatus.CREATED).body(carreraCreada);
    }

    //Obtener todas las carreras
    @GetMapping("")
    public ResponseEntity<List<CarrerasDTO>> obtenerTodasCarreras() {
        List<CarrerasDTO> carreras = carreraServicio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(carreras);
    }

    //Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarrerasDTO> obtenerCarreraPorId(@PathVariable int id) {
        CarrerasDTO carreraCreada = carreraServicio.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carreraCreada);
    }

    // Actualizar una carrera
    @PutMapping("/{id}")
    public ResponseEntity<CarrerasDTO> actualizarCarrera(@PathVariable int id, @RequestBody Carreras carrera) {
        CarrerasDTO carreraActualizada = carreraServicio.update(id, carrera);
        return ResponseEntity.status(HttpStatus.OK).body(carreraActualizada);
    }

    // Eliminar una carrera
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCarrera(@PathVariable int id) {
        try {
            boolean eliminado = carreraServicio.delete(id);
            if (eliminado) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\":\"Carrera no encontrada.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // 2h) Generar un reporte de las carreras
    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteCarreraDTO>> generarReporteCarreras() {
            List<ReporteCarreraDTO> reporte = carreraServicio.generarReporteCarreras();
            return ResponseEntity.status(HttpStatus.OK).body(reporte);
    }

    // 2f) Recuperar carreras ordenadas por cantidad de inscriptos
    @GetMapping("/ordenadas-inscriptos")
    public ResponseEntity<List<InscriptosContadorDTO>> getCarrerasOrdenadasPorInscriptos() {
            List<InscriptosContadorDTO> carreras = carreraServicio.getCarrerasOrdenadasPorInscriptos();
            return ResponseEntity.status(HttpStatus.OK).body(carreras);
    }
}
