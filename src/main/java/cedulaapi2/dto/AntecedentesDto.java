package cedulaapi2.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentesDto {
    @JsonProperty("id_antecedente")
    private UUID idAntecedentes;
    private String descripcion;
    @JsonProperty("fecha_creaciondb")
    private LocalDateTime fechacreacion = LocalDateTime.now();
    private String estado;
    private String cedulaRef;

}
