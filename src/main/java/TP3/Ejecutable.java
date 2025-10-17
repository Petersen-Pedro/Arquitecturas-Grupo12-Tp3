package TP3;

import TP3.Utils.CargarDatos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;

@SpringBootApplication
public class Ejecutable {

    public static void main(String[] args) {
        // Inicia la aplicación y devuelve el contexto
        ConfigurableApplicationContext context = SpringApplication.run(Ejecutable.class, args);

        try {
            // Obtener el bean gestionado por Spring
            CargarDatos cargaDeDatos = context.getBean(CargarDatos.class);

            // Ejecutar la carga de datos inicial
            cargaDeDatos.cargarDatosCSV();
        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}