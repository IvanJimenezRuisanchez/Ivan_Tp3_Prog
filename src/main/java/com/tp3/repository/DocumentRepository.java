package com.tp3.repository;

import com.tp3.model.Cd;
import com.tp3.model.Document;
import com.tp3.model.Dvd;
import com.tp3.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
