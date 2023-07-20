package ec.edu.espe.arquitectura.examen_gonzalez.controller;

import ec.edu.espe.arquitectura.examen_gonzalez.dto.req.PagoRolReq;
import ec.edu.espe.arquitectura.examen_gonzalez.model.PagoRol;
import ec.edu.espe.arquitectura.examen_gonzalez.service.PagoRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pago-rol")
public class PagoRolController {
    private final PagoRolService pagoRolService;

    public PagoRolController(PagoRolService pagoRolService) {
        this.pagoRolService = pagoRolService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PagoRol> create (@RequestBody PagoRolReq pagoRolReq){
        try{
            PagoRol pagoRol = this.pagoRolService.create(pagoRolReq);
            return ResponseEntity.ok(pagoRol);
        }catch (RuntimeException rte){
            return ResponseEntity.badRequest().build();
        }
    }
}
