package com.example.module_1.controller.restapi.games;

import com.example.module_1.controller.model.GameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GameRestController {
    private final GameService gameService;

    @PostMapping("/addGame")
    public void addUser(@RequestBody GameDTO gameDTO){
        gameService.addGame(gameDTO);
    }

    @PutMapping("/updateGame/{id}")
    public void updateGame(@PathVariable("id") String id, @RequestBody GameDTO gameDTO){
        gameService.updateGameById(id, gameDTO);
    }

    @GetMapping("/deleteGame/{id}")
    public String deleteUser(@PathVariable("id") String id){
        gameService.deleteGameById(id);
        return "Game has been deleted";
    }
}
