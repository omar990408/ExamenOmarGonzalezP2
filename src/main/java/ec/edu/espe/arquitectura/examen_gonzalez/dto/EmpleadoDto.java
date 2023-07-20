package ec.edu.espe.arquitectura.examen_gonzalez.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpleadoDto {
    private String cedulaIdentidad;
    private String apellidos;
    private String nombres;
    private String numeroCuenta;
}
