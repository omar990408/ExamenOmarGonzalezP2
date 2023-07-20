package ec.edu.espe.arquitectura.examen_gonzalez.controller;

import ec.edu.espe.arquitectura.examen_gonzalez.dto.EmpresaDto;
import ec.edu.espe.arquitectura.examen_gonzalez.model.Empresa;
import ec.edu.espe.arquitectura.examen_gonzalez.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Empresa> create (@RequestBody EmpresaDto empresaDto){
        try{
            Empresa empresa = this.empresaService.create(empresaDto);
            return ResponseEntity.ok(empresa);
        }catch (RuntimeException rte){
            return ResponseEntity.badRequest().build();
        }
    }
}
