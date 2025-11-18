package cedulaapi2.mapper;



import cedulaapi2.dto.AntecedentesDto;
import cedulaapi2.dto.CedulasDto;
import cedulaapi2.entity.Cedulas;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CedulasMapper {

    public static CedulasDto mapToCedulasDto(Cedulas cedulas) {
        List<AntecedentesDto> antecedentesDtos = null;
        if (cedulas.getAntecedentes() != null) {
            antecedentesDtos = cedulas.getAntecedentes().stream()
                    .map(ante -> new AntecedentesDto(
                            ante.getIdAntecedentes(),
                            ante.getDescripcion(),
                            ante.getFechacreacion(),
                            ante.getEstado(),
                            ante.getCedulaRef() != null ? ante.getCedulaRef().getCedula() : null
                    ))
                    .collect(Collectors.toList());
        }

        return new CedulasDto(
                cedulas.getId(),
                cedulas.getCedula(),
                cedulas.getNombres(),
                cedulas.getApellidos(),
                cedulas.getFechanacimiento(),
                cedulas.getEstatura(),
                cedulas.getCiudadNacimiento(),
                cedulas.getCiudadExpedicion(),
                cedulas.getFechaExpedicion(),
                cedulas.getFechaCreacionDb(),
                cedulas.getRh(),
                antecedentesDtos
        );

    }


    //solo cedula
    public static CedulasDto mapToCedulasOnlyDto(Cedulas cedulas) {
        return new CedulasDto(
                cedulas.getId(),
                cedulas.getCedula(),
                cedulas.getNombres(),
                cedulas.getApellidos(),
                cedulas.getFechanacimiento(),
                cedulas.getEstatura(),
                cedulas.getCiudadNacimiento(),
                cedulas.getCiudadExpedicion(),
                cedulas.getFechaExpedicion(),
                cedulas.getFechaCreacionDb(),
                cedulas.getRh(),
                null  // sin antecedentes
        );
    }

    public static Cedulas mapToCedulas(CedulasDto cedulasDto) {
        return new Cedulas(
                cedulasDto.getId(),
                cedulasDto.getCedula(),
                cedulasDto.getNombres(),
                cedulasDto.getApellidos(),
                cedulasDto.getFechanacimiento(),
                cedulasDto.getEstatura(),
                cedulasDto.getCiudadNacimiento(),
                cedulasDto.getCiudadExpedicion(),
                cedulasDto.getFechaExpedicion(),
                cedulasDto.getFechaCreacionDb(),
                cedulasDto.getRh(),
                null

        );
    }
}
