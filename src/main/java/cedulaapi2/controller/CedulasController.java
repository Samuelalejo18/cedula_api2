package cedulaapi2.controller;


import cedulaapi2.dto.CedulasDto;
import cedulaapi2.service.CedulasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cedulas")
public class CedulasController {

    private CedulasService cedulasService;

    @PostMapping
    public ResponseEntity<List<CedulasDto>> createCedulas(@RequestBody List<CedulasDto> cedulasList) {
        List<CedulasDto> saved = cedulasList.stream()
                .map(cedulasService::createCedulas)
                .toList();
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("{num}")
    public ResponseEntity<CedulasDto> getByCedulas(@PathVariable("num") String numCedula) {
        CedulasDto cedulasDto = cedulasService.getByCedulas(numCedula);
        return ResponseEntity.ok(cedulasDto);
    }

    @GetMapping
    public ResponseEntity<List<CedulasDto>> getAllCedulas() {
        List<CedulasDto> cedulas = cedulasService.getAllCedulas();
        return ResponseEntity.ok(cedulas);
    }

    @GetMapping("/con-antecedentes")
    public ResponseEntity<List<CedulasDto>> getAllCedulasConAntecedentes() {
        return ResponseEntity.ok(cedulasService.getAllCedulasConAntecedentes());
    }

    @GetMapping("/con-antecedentes/activos")
    public ResponseEntity<List<CedulasDto>> getAllCedulasConAntecedentesActivos() {
        List<CedulasDto> all = cedulasService.getAllCedulasConAntecedentes();

        List<CedulasDto> activos = all.stream()
                .map(c -> {
                    c.setAntecedentes(
                            c.getAntecedentes().stream()
                                    .filter(a -> "Activo".equalsIgnoreCase(a.getEstado()))
                                    .collect(Collectors.toList())
                    );
                    return c;
                })
                .filter(c -> !c.getAntecedentes().isEmpty())
                .collect(Collectors.toList());

        return ResponseEntity.ok(activos);
    }

    @GetMapping("/con-antecedentes/inactivos")
    public ResponseEntity<List<CedulasDto>> getAllCedulasConAntecedentesInactivos() {
        List<CedulasDto> all = cedulasService.getAllCedulasConAntecedentes();

        List<CedulasDto> inactivos = all.stream()
                .map(c -> {
                    c.setAntecedentes(
                            c.getAntecedentes().stream()
                                    .filter(a -> "Inactivo".equalsIgnoreCase(a.getEstado()))
                                    .collect(Collectors.toList())
                    );
                    return c;
                })
                .filter(c -> !c.getAntecedentes().isEmpty())
                .collect(Collectors.toList());

        return ResponseEntity.ok(inactivos);
    }
}
