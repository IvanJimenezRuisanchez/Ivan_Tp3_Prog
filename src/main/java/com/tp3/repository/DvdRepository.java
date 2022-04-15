package com.tp3.repository;

import com.tp3.model.Cd;
import com.tp3.model.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DvdRepository extends JpaRepository<Dvd, Integer> {
    @Query(value = "SELECT d from Dvd d WHERE upper(d.titre) = :data")
    List<Dvd> getDvdByTitre(@Param("data") String data);

    @Query(value = "SELECT d from Dvd d WHERE upper(d.auteur) = :data")
    List<Dvd> getDvdByAuteur(@Param("data") String data);

    @Query(value = "SELECT d from Dvd d WHERE upper(d.type) = :data")
    List<Dvd> getDvdByCategorie(@Param("data") String data);

    @Query(value = "SELECT d from Dvd d WHERE upper(d.anneePublication) = :data")
    List<Dvd> getDvdByAnneePublication(@Param("data") int data);
}
