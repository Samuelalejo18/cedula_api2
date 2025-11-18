package cedulaapi2.mapper;


import cedulaapi2.dto.AntecedentesDto;
import cedulaapi2.entity.Antecedentes;

public class AntecedentesMapper {

    public static AntecedentesDto mapToAntecedentesDto(Antecedentes antecedentes) {
        return new AntecedentesDto(
                antecedentes.getIdAntecedentes(),
                antecedentes.getDescripcion(),
                antecedentes.getFechacreacion(),
                antecedentes.getEstado(),
                antecedentes.getCedulaRef() != null ? antecedentes.getCedulaRef().getCedula() : null
        );
    }

    public static Antecedentes mapToAntecedentes(AntecedentesDto antecedentesDto) {

        return new Antecedentes(
                antecedentesDto.getIdAntecedentes(),
                antecedentesDto.getDescripcion(),
                antecedentesDto.getFechacreacion(),
                antecedentesDto.getEstado() != null ? antecedentesDto.getEstado() : "Activo",
                null
        );
    }
}
