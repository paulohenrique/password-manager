package com.velozient.passmanager.repository;

import com.velozient.passmanager.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

  List<Card> findByName(String name);

}
