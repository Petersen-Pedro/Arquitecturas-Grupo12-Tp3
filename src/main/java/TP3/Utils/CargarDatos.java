package TP3.Utils;

import TP3.Factory.JPAUtil;
import TP3.Modelo.*;
import TP3.Repository.*;
import java.io.*;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class CargarDatos {

    public CargarDatos(){
        cargar();
    }

    public void cargar(){
        cargarCarreras("src/main/resources/DBData/carreras.csv");
        cargarEstudiantes("src/main/resources/DBData/estudiantes.csv");
        cargarMatriculas("src/main/resources/DBData/estudianteCarrera.csv");
        System.out.println("Datos cargados correctamente");
    }

    private void cargarEstudiantes(String path){

        EntityManager em = JPAUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(path))){
            String[] linea; //
            reader.readNext(); //

            em.getTransaction().begin();

            while((linea = reader.readNext()) != null){
                Estudiante estudiante = new Estudiante();
                estudiante.setNro_documento(Long.parseLong(linea[0]));
                estudiante.setNombres(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4]);
                estudiante.setCiudad_residencia(linea[5]);
                estudiante.setNro_libreta_universitaria(linea[6]);

                em.merge(estudiante);
            }

            em.getTransaction().commit();

            System.out.println("Datos de estudiantes cargados correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private void cargarCarreras(String path){

        EntityManager em = JPAUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(path))){
            String[] linea;
            reader.readNext();

            em.getTransaction().begin();

            while((linea = reader.readNext()) != null) {
                Carreras carrera = new Carreras();
                carrera.setId_carrera(Integer.parseInt(linea[0]));
                carrera.setCarrera(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));

                em.merge(carrera);
            }

            em.getTransaction().commit();

            System.out.println("Datos de carreras cargados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private void cargarMatriculas(String path){
        EntityManager em = JPAUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] linea;
            reader.readNext();

            em.getTransaction().begin();
            while((linea = reader.readNext()) != null){

                Long dniEstudiante = Long.parseLong(linea[1]);
                Estudiante estudiante = em.find(Estudiante.class, dniEstudiante);
                int idCarrera = Integer.parseInt(linea[2]);
                Carreras carrera = em.find(Carreras.class, idCarrera);

                if (estudiante != null && carrera != null){
                    // Convertir año (Integer) a LocalDate (1 de enero de ese año)
  /*                  LocalDate inscripcion = LocalDate.of(Integer.parseInt(linea[3]), 1, 1);
                    LocalDate graduacion = "0".equals(linea[4]) ? null : LocalDate.of(Integer.parseInt(linea[4]), 1, 1);
*/
                    EstudianteCarrera matricula = new EstudianteCarrera();
                    matricula.setId(Long.parseLong(linea[0]));
                    matricula.setEstudiante(estudiante);
                    matricula.setCarrera(carrera);
                    matricula.setInscripcion(Integer.parseInt(linea[3]));
                    matricula.setGraduacion(Integer.parseInt(linea[4]));
                    matricula.setAntiguedad(Integer.parseInt(linea[5]));

                    em.merge(matricula);


                } else {
                    System.out.println("Estudiante o Carrera no encontrado");
                }
            }
            System.out.println("Matriculas cargada correctamente");
            em.getTransaction().commit();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
/*



@Service
public class CargaDeDatos {

    @Autowired
    private RepoCarrera rc;
    @Autowired
    private RepoEstudiante re;
    @Autowired
    private RepoEstudianteCarrera rec;

    public void cargarDatosDesdeCSV() throws IOException {
        File carrerasCSV = ResourceUtils.getFile("classpath:csv/carreras.csv");
        File estudiantesCSV = ResourceUtils.getFile("classpath:csv/estudiantes.csv");
        File estudianteCarreraCSV = ResourceUtils.getFile("classpath:csv/estudianteCarrera.csv");

        try (FileReader readerCarreras = new FileReader(carrerasCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readerCarreras)) {

            for (CSVRecord csvRecord : csvParser) {
                // Creación de la entidad Carrera
                Carrera carrera = new Carrera(
                        Integer.parseInt(csvRecord.get("id_carrera")),
                        csvRecord.get("nombre")
                );
                // Guardar carrera en la base de datos
                this.rc.saveAndFlush(carrera);
            }
        }

        try (FileReader readerEstudiantes = new FileReader(estudiantesCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readerEstudiantes)) {

            for (CSVRecord csvRecord : csvParser) {
                // Creación de la entidad Estudiante
                Estudiante estudiante = new Estudiante(
                        Integer.parseInt(csvRecord.get("DNI")),
                        csvRecord.get("nombre"),
                        csvRecord.get("apellido"),
                        Integer.parseInt(csvRecord.get("edad")),
                        csvRecord.get("genero"),
                        csvRecord.get("ciudad"),
                        Long.parseLong(csvRecord.get("LU"))
                );

                re.save(estudiante); // Guardar estudiante en la base de datos
            }
        }

        try (FileReader readerEstudianteCarrera = new FileReader(estudianteCarreraCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readerEstudianteCarrera)) {

            for (CSVRecord csvRecord : csvParser) {
                // Manejo del Optional para el estudiante
                Estudiante estudiante = re.findById(Integer.parseInt(csvRecord.get("id_estudiante")))
                        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id=" + csvRecord.get("id_estudiante") + "!"));

                // Manejo del Optional para la carrera
                Carrera carrera = rc.findById(Integer.parseInt(csvRecord.get("id_carrera")))
                        .orElseThrow(() -> new RuntimeException("Carrera no encontrada con id=" + csvRecord.get("id_carrera") + "!"));

                // Creación de la entidad EstudianteCarrera
                EstudianteCarrera estudianteCarrera = new EstudianteCarrera(
                        Integer.parseInt(csvRecord.get("id")),
                        estudiante,
                        carrera,
                        Integer.parseInt(csvRecord.get("anioInscripcion")),
                        Integer.parseInt(csvRecord.get("anioEgreso")),
                        Integer.parseInt(csvRecord.get("antiguedad")),
                        Boolean.parseBoolean(csvRecord.get("graduado"))
                );

                // Guardar la inscripción en la base de datos
                rec.save(estudianteCarrera);
            }
        }
    }
}*/