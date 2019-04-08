package com.gamestore.ualison.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gamestore.ualison.springboot2.model.Game;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface GameRepository extends JpaRepository<Game, Long>{

}
