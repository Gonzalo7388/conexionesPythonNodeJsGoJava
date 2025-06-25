package com.apps.mongodemo.repository;

import com.apps.mongodemo.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    // Spring Data MongoDB automatically provides CRUD operations (save, findById, findAll, delete, etc.)
    // You can add custom query methods here if needed, e.g., findByNombre(String nombre);
}