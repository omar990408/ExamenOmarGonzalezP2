package ec.edu.espe.arquitectura.examen_gonzalez.service;

import ec.edu.espe.arquitectura.examen_gonzalez.dto.req.EmpleadosPagoReq;
import ec.edu.espe.arquitectura.examen_gonzalez.dto.req.PagoRolReq;
import ec.edu.espe.arquitectura.examen_gonzalez.dto.res.PagoRolRes;
import ec.edu.espe.arquitectura.examen_gonzalez.model.EmpleadosPago;
import ec.edu.espe.arquitectura.examen_gonzalez.model.PagoRol;
import ec.edu.espe.arquitectura.examen_gonzalez.repository.PagoRolRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoRolService {
    private final PagoRolRepository pagoRolRepository;

    public PagoRolService(PagoRolRepository pagoRolRepository) {
        this.pagoRolRepository = pagoRolRepository;
    }

    public PagoRol create(PagoRolReq pagoRolReq){
        PagoRol pagoRol = toPagoTol(pagoRolReq);
        return pagoRolRepository.save(pagoRol);
    }

    public PagoRolRes validar(Month mes, String rucEmpresa){
        PagoRol pagoRol = pagoRolRepository.findByRucEmpresaAndMes(rucEmpresa,mes.toString());
        return null;
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

}
