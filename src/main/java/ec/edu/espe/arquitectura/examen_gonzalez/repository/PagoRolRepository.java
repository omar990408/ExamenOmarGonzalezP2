package ec.edu.espe.arquitectura.examen_gonzalez.repository;

import ec.edu.espe.arquitectura.examen_gonzalez.model.PagoRol;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Month;
import java.util.Date;

public interface PagoRolRepository extends MongoRepository<PagoRol, String> {
    PagoRol findByRucEmpresaAndMes(String rucEmpresa, Month mes);
}
