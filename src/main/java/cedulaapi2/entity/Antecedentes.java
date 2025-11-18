package cedulaapi2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antecedentes")
public class Antecedentes {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idAntecedentes", updatable = false, nullable = false)
    private UUID idAntecedentes;

    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @Column(name = "fecha_creacion_bd")
    private LocalDateTime fechacreacion = LocalDateTime.now();

    @Column(name = "estado", length = 10)
    private String estado = "Activo";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cedula",
            referencedColumnName = "cedula",
            foreignKey = @ForeignKey(name = "fk_antecedentes_cedulas")
    )
    private Cedulas cedulaRef;
}
