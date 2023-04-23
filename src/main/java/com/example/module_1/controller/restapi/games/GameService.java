package com.example.module_1.controller.restapi.games;

import com.example.module_1.controller.model.GameDTO;
import com.example.module_1.controller.model.UserDTO;
import com.example.module_1.controller.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final MongoTemplate mongoTemplate;

    public void addGame(GameDTO gameDTO){
        gameRepository.save(gameDTO);
    }

    public GameDTO getGameById(String id) {
        Optional<GameDTO> gameDTO = gameRepository.findById(id);
        return gameDTO.orElseGet(GameDTO::new);
    }

    public List<GameDTO> getGameByGenre(String genre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is(genre));
        return mongoTemplate.find(query, GameDTO.class);
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll();
    }

    public void deleteGameById(String id) {
        gameRepository.deleteById(id);
    }

    public void updateGameById(String id, GameDTO gameDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update().set("name", gameDto.name);
        Update updateSecond = new Update().set("description", gameDto.description);
        Update updateThird = new Update().set("genre", gameDto.genre);

        mongoTemplate.updateFirst(query, update, GameDTO.class);
        mongoTemplate.updateFirst(query, updateSecond, GameDTO.class);
        mongoTemplate.updateFirst(query, updateThird, GameDTO.class);
    }
}
