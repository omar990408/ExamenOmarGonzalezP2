package ec.edu.espe.arquitectura.examen_gonzalez.repository;

import ec.edu.espe.arquitectura.examen_gonzalez.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpresaRepository extends MongoRepository<Empresa, String>{
}
