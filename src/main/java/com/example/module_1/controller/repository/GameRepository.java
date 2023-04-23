package com.example.module_1.controller.repository;

import com.example.module_1.controller.model.GameDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<GameDTO, String> {
}
