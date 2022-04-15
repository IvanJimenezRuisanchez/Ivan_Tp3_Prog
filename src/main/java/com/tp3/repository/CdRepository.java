package com.tp3.repository;

import com.tp3.model.Cd;
import com.tp3.model.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CdRepository extends JpaRepository<Cd, Integer> {
    @Query(value = "SELECT cd from Cd cd WHERE upper(cd.titre) = :data")
    List<Cd> getCdByTitre(@Param("data") String data);

    @Query(value = "SELECT cd from Cd cd WHERE upper(cd.auteur) = :data")
    List<Cd> getCdByAuteur(@Param("data") String data);

    @Query(value = "SELECT cd from Cd cd WHERE upper(cd.type) =  :data")
    List<Cd> getCdByCategorie(@Param("data") String data);

    @Query(value = "SELECT cd from Cd cd WHERE upper(cd.anneePublication) = :data")
    List<Cd> getCdByAnneePublication(@Param("data") int data);

}
