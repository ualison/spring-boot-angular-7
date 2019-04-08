package com.gamestore.ualison.springboot2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gamestore.ualison.springboot2.exception.ResourceNotFoundException;
import com.gamestore.ualison.springboot2.model.Game;
import com.gamestore.ualison.springboot2.repository.GameRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
	@Autowired
	private GameRepository gameRepository;

	@GetMapping("/game")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}

	@GetMapping("/game/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Game> getGameById(@PathVariable(value = "id") Long gameId)
			throws ResourceNotFoundException {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found for this id :: " + gameId));
		return ResponseEntity.ok().body(game);
	}

	@PostMapping("/game")
	@CrossOrigin(origins = "http://localhost:4200")
	public Game createGame(@Valid @RequestBody Game game) {
		return gameRepository.save(game);
	}

	@PutMapping("/game/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Game> updateGame(@PathVariable(value = "id") Long gameId,
			@Valid @RequestBody Game gameDetails) throws ResourceNotFoundException {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found for this id :: " + gameId));

		game.setPrice(gameDetails.getPrice());
		game.setPublisher(gameDetails.getPublisher());
		game.setName(gameDetails.getName());
		final Game updatedGame = gameRepository.save(game);
		return ResponseEntity.ok(updatedGame);
	}

	@DeleteMapping("/game/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Map<String, Boolean> deleteGame(@PathVariable(value = "id") Long gameId)
			throws ResourceNotFoundException {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found for this id :: " + gameId));

		gameRepository.delete(game);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
