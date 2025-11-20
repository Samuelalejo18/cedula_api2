package cedulaapi2.service;


import cedulaapi2.dto.CedulasDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CedulasService {
    CedulasDto createCedulas(CedulasDto cedulasDto);

    CedulasDto getByCedulas(String numCedula);

    List<CedulasDto> getAllCedulas();

    List<CedulasDto> getAllCedulasConAntecedentes();

    List<CedulasDto> getAllCedulasSinAntecedentes();

}

