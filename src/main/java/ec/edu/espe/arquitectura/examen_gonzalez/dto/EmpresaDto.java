package ec.edu.espe.arquitectura.examen_gonzalez.dto;

import ec.edu.espe.arquitectura.examen_gonzalez.model.Empleado;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpresaDto {
    private String ruc;
    private String razonSocial;
    private String cuentaPrincipal;
    private List<EmpleadoDto> empleados;
}
