package cedulaapi2.repository;


import cedulaapi2.entity.Cedulas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CedulasRepository extends JpaRepository<Cedulas, UUID> {
    Optional<Cedulas> findByCedula(String cedula);

    @Query("SELECT DISTINCT c FROM Cedulas c JOIN FETCH c.antecedentes a")
    List<Cedulas> findCedulasConAntecedentes();

    @Query("SELECT c FROM Cedulas c LEFT JOIN c.antecedentes a WHERE a.cedulaRef IS NULL")
    List<Cedulas> findCedulasSinAntecedentes();

}
