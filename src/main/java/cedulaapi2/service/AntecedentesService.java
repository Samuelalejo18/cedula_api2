package cedulaapi2.service;



import cedulaapi2.dto.AntecedentesDto;

import java.util.List;
import java.util.UUID;

public interface AntecedentesService {

    AntecedentesDto createAntecedentes(AntecedentesDto antecedentesDto);

    AntecedentesDto updateAntecedentes(UUID idAntecedentes, AntecedentesDto updateAntecedentes);
}
