package cedulaapi2.service.impl;

import cedulaapi2.dto.AntecedentesDto;
import cedulaapi2.dto.CedulasDto;
import cedulaapi2.entity.Antecedentes;
import cedulaapi2.entity.Cedulas;
import cedulaapi2.exception.ResourceNotFoundException;
import cedulaapi2.mapper.AntecedentesMapper;
import cedulaapi2.mapper.CedulasMapper;
import cedulaapi2.repository.AntecedentesRepository;
import cedulaapi2.repository.CedulasRepository;
import cedulaapi2.service.AntecedentesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AntecedentesServiceImpl implements AntecedentesService {

    private AntecedentesRepository antecedentesRepository;
    private CedulasRepository cedulasRepository;


    @Override
    public AntecedentesDto createAntecedentes(AntecedentesDto antecedentesDto) {
        Antecedentes antecedentes = AntecedentesMapper.mapToAntecedentes(antecedentesDto);

        Cedulas cedula = cedulasRepository.findByCedula(antecedentesDto.getCedulaRef())
                .orElseThrow(() -> new RuntimeException("No existe la cédula: " + antecedentesDto.getCedulaRef()));

        antecedentes.setCedulaRef(cedula);

        Antecedentes saved = antecedentesRepository.save(antecedentes);

        return AntecedentesMapper.mapToAntecedentesDto(saved);

    }

    @Override
    public AntecedentesDto updateAntecedentes(UUID idAntecedentes, AntecedentesDto updateAntecedentes) {
        Antecedentes antecedentes = antecedentesRepository.findById(idAntecedentes)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el antecedente: " + idAntecedentes));

        antecedentes.setEstado(updateAntecedentes.getEstado());

        Antecedentes updateAntesedente = antecedentesRepository.save(antecedentes);
        return AntecedentesMapper.mapToAntecedentesDto(updateAntesedente);
    }

    @Override
    public List<CedulasDto> getAllCedulasSinAntecedentes() {
        List<Cedulas> cedulas = cedulasRepository.findCedulasSinAntecedentes();
        return cedulas.stream()
                .map(CedulasMapper::mapToCedulasOnlyDto) // Sin antecedentes
                .collect(Collectors.toList());
    }
}
