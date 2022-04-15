package com.tp3.repository;

import com.tp3.model.Dvd;
import com.tp3.model.Empreunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpreuntRepository extends JpaRepository<Empreunt, Integer> {
    @Query(value = "SELECT e from Empreunt e WHERE e.client.idUser = :user AND e.document.idDocument = :document")
    Empreunt getEmpreuntByClientAndDocument(@Param("user") int idUser, @Param("document") int idDocument);

    @Query(value = "SELECT e from Empreunt e WHERE e.client.idUser = :user")
    List<Empreunt> getEmpreuntsByIdUser(@Param("user") int idUser);
}
