package com.tp3.repository;

import com.tp3.model.Empreunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpreuntRepository extends JpaRepository<Empreunt, Long> {
}
