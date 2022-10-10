package com.velozient.passmanager.controller;

import com.velozient.passmanager.model.Card;
import com.velozient.passmanager.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/password-cards")
public class CardController {

  @Autowired
  CardRepository cardRepository;

  @GetMapping
  public ResponseEntity<List<Card>> getAllCards(@RequestParam(required = false) String name) {
    try {
      List<Card> cards = new ArrayList<Card>();

      if (name == null) {
        cardRepository.findAll().forEach(cards::add);
      } else {
        cardRepository.findByName(name).forEach(cards::add);
      }

      if (cards.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(cards, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Card> getCardById(@PathVariable("id") long id) {
    Optional<Card> cardData = cardRepository.findById(id);

    if (cardData.isPresent()) {
      return new ResponseEntity<>(cardData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Card> createCard(@RequestBody Card card) {
    try {
      Card _card = cardRepository.save(card);
      return new ResponseEntity<>(_card, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Card> updateCard(@PathVariable("id") long id, @RequestBody Card card) {
    Optional<Card> cardData = cardRepository.findById(id);

    if (cardData.isPresent()) {
      Card _card = cardData.get();
      _card.setName(card.getName());
      _card.setUrl(card.getUrl());
      _card.setUsername(card.getUsername());
      _card.setPassword(card.getPassword());
      return new ResponseEntity<>(cardRepository.save(_card), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCard(@PathVariable("id") long id) {
    try {
      cardRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



}
