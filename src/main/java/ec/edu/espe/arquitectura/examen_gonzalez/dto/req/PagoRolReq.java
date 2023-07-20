package ec.edu.espe.arquitectura.examen_gonzalez.dto.req;

import ec.edu.espe.arquitectura.examen_gonzalez.model.EmpleadosPago;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class PagoRolReq {
    private Month mes;
    private String rucEmpresa;
    private String cuentaPrincipal;
    private List<EmpleadosPagoReq> empleadosPago;
}
