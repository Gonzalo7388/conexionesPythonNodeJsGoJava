package com.apps.mongodemo;

import com.apps.mongodemo.model.Contacto;
import com.apps.mongodemo.model.Curso;
import com.apps.mongodemo.model.Estudiante;
import com.apps.mongodemo.repository.EstudianteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired; // Add this import
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MongodemoApplication implements CommandLineRunner {

    @Autowired // Spring will inject an instance of EstudianteRepository here
    private EstudianteRepository estudianteRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Iniciando operación de inserción en MongoDB ---");

        // Clear previous data if needed (optional, for clean runs)
        // estudianteRepository.deleteAll();
        // System.out.println("Colección 'estudiantes' vaciada (si existía).");

        // Create the "Leon Scoot Kennedy" student object
        Contacto leonContacto = new Contacto("gon.freecss@example.com", "987652221");
        List<Curso> leonCursos = Arrays.asList(
                new Curso("Inteligencia Artificial", 5),
                new Curso("Seguridad Informática", 4)
        );
        Estudiante leon = new Estudiante(
                "Gon Freecss",
                20,
                "Ciencias de la Computación",
                10,
                true,
                leonContacto,
                leonCursos
        );

        // Save the student to the database
        Estudiante savedEstudiante = estudianteRepository.save(leon);
        System.out.println("Base de datos 'universidad' y colección 'estudiantes' utilizadas/creadas exitosamente.");
        System.out.println("Documento insertado con ID: " + savedEstudiante.getId());
        System.out.println("Documento insertado: " + savedEstudiante); // Prints the full object

        System.out.println("--- Operación de inserción finalizada ---");
    }
}