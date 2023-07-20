package ec.edu.espe.arquitectura.examen_gonzalez.service;

import ec.edu.espe.arquitectura.examen_gonzalez.dto.EmpleadoDto;
import ec.edu.espe.arquitectura.examen_gonzalez.dto.EmpresaDto;
import ec.edu.espe.arquitectura.examen_gonzalez.model.Empleado;
import ec.edu.espe.arquitectura.examen_gonzalez.model.Empresa;
import ec.edu.espe.arquitectura.examen_gonzalez.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa create(EmpresaDto empresaDto){
        Empresa empresa = toEmpresa(empresaDto);
        return this.empresaRepository.save(empresa);
    }

    private Empresa toEmpresa(EmpresaDto empresaDto) {

        return Empresa.builder()
                .ruc(empresaDto.getRuc())
                .razonSocial(empresaDto.getRazonSocial())
                .cuentaPrincipal(empresaDto.getCuentaPrincipal())
                .empleados(toEmpleadoList(empresaDto.getEmpleados()))
                .build();
    }

    private List<Empleado> toEmpleadoList(List<EmpleadoDto> empleadosDto) {
        return empleadosDto.stream()
                .map(empleadoDto -> Empleado.builder()
                        .cedulaIdentidad(empleadoDto.getCedulaIdentidad())
                        .nombres(empleadoDto.getNombres())
                        .apellidos(empleadoDto.getApellidos())
                        .numeroCuenta(empleadoDto.getNumeroCuenta())
                        .build())
                .collect(Collectors.toList());
    }
}
