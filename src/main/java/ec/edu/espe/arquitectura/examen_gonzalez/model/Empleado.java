package ec.edu.espe.arquitectura.examen_gonzalez.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Empleado {
    private String cedulaIdentidad;
    private String apellidos;
    private String nombres;
    private String numeroCuenta;
}
