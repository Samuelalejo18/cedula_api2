package cedulaapi2.controller;


import cedulaapi2.dto.AntecedentesDto;
import cedulaapi2.service.AntecedentesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/antecedentes")
public class AntecedentesController {

    private AntecedentesService antecedentesService;

    @PostMapping
    public ResponseEntity<AntecedentesDto> createAntecedentes(@RequestBody AntecedentesDto antecedentesDto) {
        AntecedentesDto savedAntecedentes = antecedentesService.createAntecedentes(antecedentesDto);
        return new ResponseEntity<>(savedAntecedentes, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<AntecedentesDto> updateAntecedentes(@PathVariable("id") UUID idAntecedentes,
                                                              @RequestBody AntecedentesDto updateAntecedentes) {
        AntecedentesDto antecedentesDto = antecedentesService.updateAntecedentes(idAntecedentes, updateAntecedentes);
        return ResponseEntity.ok(antecedentesDto);
    }
}
