package com.tp3.repository;

import com.tp3.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Integer> {
    @Query(value = "SELECT l from Livre l  JOIN  Document d  ON l.idDocument = d.idDocument WHERE upper(d.titre) = :data")
    List<Livre> getLivreByTitre(@Param("data") String data);

    @Query(value = "SELECT l from Livre l WHERE upper(l.auteur) = :data")
    List<Livre> getLivreAuteur(@Param("data") String data);

    @Query(value = "SELECT l from Livre l WHERE upper(l.type) = :data")
    List<Livre> getLivreByCategorie(@Param("data") String data);

    @Query(value = "SELECT l from Livre l WHERE l.anneePublication = :data")
    List<Livre> getLivreByAnneePublication(@Param("data") int data);
}
