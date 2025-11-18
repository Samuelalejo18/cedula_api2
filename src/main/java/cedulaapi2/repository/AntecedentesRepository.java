package cedulaapi2.repository;


import cedulaapi2.entity.Antecedentes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AntecedentesRepository extends JpaRepository<Antecedentes, UUID> {

    List<Antecedentes> findByCedulaRef_CedulaAndEstado(String cedula, String estado);

}
