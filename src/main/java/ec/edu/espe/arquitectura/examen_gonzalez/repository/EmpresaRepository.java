package ec.edu.espe.arquitectura.examen_gonzalez.repository;

import ec.edu.espe.arquitectura.examen_gonzalez.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmpresaRepository extends MongoRepository<Empresa, String>{
    Optional<Empresa> findByRuc(String ruc);
}
