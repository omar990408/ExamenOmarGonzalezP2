package ec.edu.espe.arquitectura.examen_gonzalez.dto.res;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;

@Data
@Builder
public class PagoRolRes {
    private Month mes;
    private String rucEmpresa;
    private BigDecimal valorTotal;
    private BigDecimal valorReal;
    private Integer totalTransacciones;
    private Integer errores;
}
