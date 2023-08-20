package ec.edu.espe.arquitectura.examen_gonzalez.service;

import ec.edu.espe.arquitectura.examen_gonzalez.dto.req.EmpleadosPagoReq;
import ec.edu.espe.arquitectura.examen_gonzalez.dto.req.PagoRolReq;
import ec.edu.espe.arquitectura.examen_gonzalez.dto.res.PagoRolRes;
import ec.edu.espe.arquitectura.examen_gonzalez.model.Empleado;
import ec.edu.espe.arquitectura.examen_gonzalez.model.EmpleadosPago;
import ec.edu.espe.arquitectura.examen_gonzalez.model.Empresa;
import ec.edu.espe.arquitectura.examen_gonzalez.model.PagoRol;
import ec.edu.espe.arquitectura.examen_gonzalez.repository.EmpresaRepository;
import ec.edu.espe.arquitectura.examen_gonzalez.repository.PagoRolRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoRolService {
    private final PagoRolRepository pagoRolRepository;
    private final EmpresaRepository empresaRepository;

    public PagoRolService(PagoRolRepository pagoRolRepository, EmpresaRepository empresaRepository) {
        this.pagoRolRepository = pagoRolRepository;
        this.empresaRepository = empresaRepository;
    }

    public PagoRol create(PagoRolReq pagoRolReq){
        PagoRol pagoRol = toPagoTol(pagoRolReq);
        return pagoRolRepository.save(pagoRol);
    }

    public PagoRolRes validar(Month mes, String rucEmpresa){
        PagoRol pagoRol = pagoRolRepository.findByRucEmpresaAndMes(rucEmpresa, mes);
        if (pagoRol == null){
            throw new RuntimeException("Pago rol no existe");
        }else {
            Optional<Empresa> empresa = empresaRepository.findByRuc(rucEmpresa);
            if (empresa.isEmpty()){
                throw new RuntimeException("Empresa no existe");
            }else{
                List<EmpleadosPago> empleadosPago = pagoRol.getEmpleadosPago();
                List<Empleado> empleados = empresa.get().getEmpleados();
                for (EmpleadosPago empleadosPago1 : empleadosPago) {
                    for (Empleado empleado : empleados) {
                        if (empleadosPago1.getNumeroCuenta().equals(empleado.getNumeroCuenta())){
                            empleadosPago1.setEstado("VAL");
                            break;
                        }else {
                            empleadosPago1.setEstado("BAD");
                        }
                    }
                }
                pagoRol.setEmpleadosPago(empleadosPago);
                pagoRol.setValorReal(BigDecimal.valueOf(getValorReal(empleadosPago)));
                pagoRolRepository.save(pagoRol);
                return PagoRolRes.builder()
                        .mes(pagoRol.getMes())
                        .rucEmpresa(pagoRol.getRucEmpresa())
                        .valorTotal(pagoRol.getValorTotal())
                        .valorReal(pagoRol.getValorReal())
                        .totalTransacciones(countTransacciones(pagoRol.getEmpleadosPago()))
                        .errores(countBadTransacciones(pagoRol.getEmpleadosPago()))
                        .build();
            }
        }
    }

    private Integer countBadTransacciones(List<EmpleadosPago> empleadosPago) {
        int count = 0;
        for (EmpleadosPago empleadosPago1 : empleadosPago) {
            if (empleadosPago1.getEstado().equals("BAD")){
                count++;
            }
        }
        return count;
    }

    private Integer countTransacciones(List<EmpleadosPago> empleadosPago) {
        int count = 0;
        for (EmpleadosPago empleadosPago1 : empleadosPago) {
            if (empleadosPago1.getEstado().equals("VAL")){
                count++;
            }
        }
        return count;
    }

    private PagoRol toPagoTol(PagoRolReq pagoRolReq) {
        return PagoRol.builder()
                .mes(pagoRolReq.getMes())
                .fechaProceso(Calendar.getInstance().getTime())
                .rucEmpresa(pagoRolReq.getRucEmpresa())
                .cuentaPrincipal(pagoRolReq.getCuentaPrincipal())
                .valorTotal(BigDecimal.valueOf(getValorTotal(pagoRolReq.getEmpleadosPago())))
                .valorReal(BigDecimal.valueOf(0))
                .empleadosPago(ToEmpleadosPagoList(pagoRolReq.getEmpleadosPago()))
                .build();
    }

    private List<EmpleadosPago> ToEmpleadosPagoList(List<EmpleadosPagoReq> empleadosPago) {
        return empleadosPago.stream()
                .map(empleadosPagoReq -> EmpleadosPago.builder()
                        .numeroCuenta(empleadosPagoReq.getNumeroCuenta())
                        .valor(empleadosPagoReq.getValor())
                        .estado("PEN")
                        .build())
                .collect(Collectors.toList());
    }

    private Double getValorTotal(List<EmpleadosPagoReq> empleadosPagoReqs){
        double valorTotal = 0.0;
        for (EmpleadosPagoReq empleadosPagoReq : empleadosPagoReqs) {
            valorTotal += empleadosPagoReq.getValor().doubleValue();
        }
        return valorTotal;
    }

    private Double getValorReal(List<EmpleadosPago> empleadosPago){
        double valorTotal = 0.0;
        for (EmpleadosPago empleadosPago1 : empleadosPago) {
            if (empleadosPago1.getEstado().equals("VAL")){
                valorTotal += empleadosPago1.getValor().doubleValue();
            }
        }
        return valorTotal;
    }

}
