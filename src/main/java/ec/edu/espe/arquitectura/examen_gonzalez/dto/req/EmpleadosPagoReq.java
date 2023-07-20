package ec.edu.espe.arquitectura.examen_gonzalez.dto.req;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EmpleadosPagoReq {
    private String numeroCuenta;
    private BigDecimal valor;
}
