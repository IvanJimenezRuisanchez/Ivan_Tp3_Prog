package com.tp3.repository;

import com.tp3.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ClientRepository extends JpaRepository <Client, Integer> {
}
