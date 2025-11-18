package cedulaapi2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CedulasDto {
    private UUID id;
    private String cedula;
    private String nombres;
    private String apellidos;

    @JsonProperty("fecha_nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechanacimiento;
    private String estatura;
    @JsonProperty("ciudad_nacimiento")
    private String ciudadNacimiento;
    @JsonProperty("ciudad_expedicion")
    private String ciudadExpedicion;
    @JsonProperty("fecha_expedicion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaExpedicion;
    private LocalDateTime fechaCreacionDb = LocalDateTime.now();
    private String rh;
    private List<AntecedentesDto> antecedentes;
}
