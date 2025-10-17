package TP3.Utils;

import TP3.Modelo.*;
import TP3.Repository.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CargarDatos {

    private final CarrerasRepository carreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteCarreraRepository estudianteCarreraRepository;

    @Autowired
    public CargarDatos(CarrerasRepository carreraRepository,
                       EstudianteRepository estudianteRepository,
                       EstudianteCarreraRepository estudianteCarreraRepository) {
        this.carreraRepository = carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.estudianteCarreraRepository = estudianteCarreraRepository;
    }

    public void cargarDatosCSV() throws IOException {
        cargarCarreras();
        cargarEstudiantes();
        cargarInscripciones();
    }

    private void cargarCarreras() throws IOException {
        File carrerasCSV = ResourceUtils.getFile("classpath:DBData/carreras.csv");

        try (FileReader reader = new FileReader(carrerasCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord record : csvParser) {
                Carreras carrera = new Carreras();
                carrera.setId_carrera(Integer.parseInt(record.get("id_carrera")));
                carrera.setCarrera(record.get("carrera"));
                carrera.setDuracion(Integer.parseInt(record.get("duracion")));
                carreraRepository.save(carrera);
            }
        }
    }

    private void cargarEstudiantes() throws IOException {
        File estudiantesCSV = ResourceUtils.getFile("classpath:DBData/estudiantes.csv");

        try (FileReader reader = new FileReader(estudiantesCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord record : csvParser) {
                Estudiante estudiante = new Estudiante();
                estudiante.setNro_documento(record.get("DNI"));
                estudiante.setNombres(record.get("nombre"));
                estudiante.setApellido(record.get("apellido"));
                estudiante.setEdad(Integer.parseInt(record.get("edad")));
                estudiante.setGenero(record.get("genero"));
                estudiante.setCiudad_residencia(record.get("ciudad"));
                estudiante.setNro_libreta_universitaria(record.get("LU"));

                estudianteRepository.save(estudiante);
            }
        }
    }

    private void cargarInscripciones() throws IOException {
        File inscripcionesCSV = ResourceUtils.getFile("classpath:DBData/estudianteCarrera.csv");

        try (FileReader reader = new FileReader(inscripcionesCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord record : csvParser) {
                // Obtener estudiante por DNI (nro_documento)
                Estudiante estudiante = estudianteRepository.findById(record.get("id_estudiante"))
                        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con DNI: " + record.get("id_estudiante")));

                // Obtener carrera por ID
                Carreras carrera = carreraRepository.findById(Integer.parseInt(record.get("id_carrera")))
                        .orElseThrow(() -> new RuntimeException("Carrera no encontrada con ID: " + record.get("id_carrera")));

                // Crear y guardar la relación EstudianteCarrera
                EstudianteCarrera inscripcion = new EstudianteCarrera();
                inscripcion.setId(Long.parseLong(record.get("id")));
                inscripcion.setEstudiante(estudiante);
                inscripcion.setCarrera(carrera);

                int anioInscripcion = Integer.parseInt(record.get("inscripcion"));
                inscripcion.setInscripcion(anioInscripcion);

                // Manejar anioEgreso (puede ser nulo)
                String anioEgresoStr = record.get("graduacion");
                if (anioEgresoStr != null && !anioEgresoStr.isEmpty() && !anioEgresoStr.equals("0")) {
                    inscripcion.setGraduacion(Integer.parseInt(anioEgresoStr));
                }

                // Manejar antigüedad
                inscripcion.setAntiguedad(Integer.parseInt(record.get("antiguedad")));

                estudianteCarreraRepository.save(inscripcion);
            }
        }
    }


}


























