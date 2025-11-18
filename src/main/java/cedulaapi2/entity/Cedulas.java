package cedulaapi2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cedulas")

public class Cedulas {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "cedula", unique = true, length = 15)
    private String cedula;
    @Column(name = "nombres", length = 50)
    private String nombres;
    @Column(name = "apellidos", length = 50)
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechanacimiento;
    @Column(name = "estatura", length = 8)
    private String estatura;
    @Column(name = "ciudad_nacimiento", length = 40)
    private String ciudadNacimiento;
    @Column(name = "ciudad_expedicion", length = 40)
    private String ciudadExpedicion;
    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;
    @Column(name = "fecha_creacion_bd")
    private LocalDateTime fechaCreacionDb = LocalDateTime.now();
    @Column(name = "rh", length = 4)
    private String rh;
    @OneToMany(mappedBy = "cedulaRef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Antecedentes> antecedentes;

}
