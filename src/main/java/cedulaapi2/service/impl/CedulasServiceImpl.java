package cedulaapi2.service.impl;


import cedulaapi2.dto.AntecedentesDto;
import cedulaapi2.dto.CedulasDto;
import cedulaapi2.entity.Antecedentes;
import cedulaapi2.entity.Cedulas;
import cedulaapi2.exception.ResourceNotFoundException;
import cedulaapi2.mapper.CedulasMapper;
import cedulaapi2.repository.CedulasRepository;
import cedulaapi2.service.CedulasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CedulasServiceImpl implements CedulasService {

    private CedulasRepository cedulasRepository;

    @Override
    public CedulasDto createCedulas(CedulasDto cedulasDto) {

        Cedulas cedulas = CedulasMapper.mapToCedulas(cedulasDto);
        Cedulas savedCedulas = cedulasRepository.save(cedulas);
        return CedulasMapper.mapToCedulasDto(savedCedulas);
    }

    @Override
    public CedulasDto getByCedulas(String numCedula) {
        Cedulas cedula = cedulasRepository.findByCedula(numCedula)
                .orElseThrow(() -> new ResourceNotFoundException("La cédula no existe: " + numCedula));

        cedula.setAntecedentes(
                cedula.getAntecedentes().stream()
                        .filter(a -> "Activo".equalsIgnoreCase(a.getEstado()))
                        .collect(Collectors.toList())
        );

        if (cedula.getAntecedentes().isEmpty()) {
            Antecedentes sinAnt = new Antecedentes();
            sinAnt.setDescripcion("Sin antecedentes");
            sinAnt.setEstado("N/A");
            cedula.setAntecedentes(List.of(sinAnt));
        }

        return CedulasMapper.mapToCedulasDto(cedula);
    }


    @Override
    public List<CedulasDto> getAllCedulas() {
        List<Cedulas> cedulas = cedulasRepository.findAll();
        return cedulas.stream()
                .map(CedulasMapper::mapToCedulasOnlyDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<CedulasDto> getAllCedulasConAntecedentes() {
        List<Cedulas> cedulas = cedulasRepository.findCedulasConAntecedentes();

        return cedulas.stream()
                .map(c -> {
                    CedulasDto dto = CedulasMapper.mapToCedulasDto(c);
                    dto.setAntecedentes(
                            c.getAntecedentes().stream()
                                    .map(a -> new AntecedentesDto(
                                            a.getIdAntecedentes(),
                                            a.getDescripcion(),
                                            a.getFechacreacion(),
                                            a.getEstado(),
                                            c.getCedula()
                                    ))
                                    .collect(Collectors.toList())
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }



    /*

    public CedulasDto getByCedulas(String numCedula) {
        Cedulas cedulas = cedulasRepository.findByCedula(numCedula)
                .orElseThrow( () ->
                        new ResourceNotFoundException("La cedula no existe" + numCedula));

        return CedulasMapper.mapToCedulasDto(cedulas);
    }
    * */

}
