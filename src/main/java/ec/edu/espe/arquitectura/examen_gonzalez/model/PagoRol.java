package ec.edu.espe.arquitectura.examen_gonzalez.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "pago_rol")
public class PagoRol {
    @Id
    private String id;
    private Month mes;
    private Date fechaProceso;
    private String rucEmpresa;
    private String cuentaPrincipal;
    private BigDecimal valorTotal;
    private BigDecimal valorReal;
    private List<EmpleadosPago> empleadosPago;
    @Version
    private Long version;

}
