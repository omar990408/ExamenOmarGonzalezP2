package ec.edu.espe.arquitectura.examen_gonzalez.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "empresa")
public class Empresa {
    @Id
    private String id;
    private String ruc;
    private String razonSocial;
    private String cuentaPrincipal;
    private List<Empleado> empleados;
    @Version
    private Long version;
}
