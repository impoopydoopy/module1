package com.example.module_1.controller.restapi.games;

import com.example.module_1.controller.model.GameDTO;
import com.example.module_1.controller.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/games/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {
        GameDTO gameDTO = gameService.getGameById(id);
        model.addAttribute("gameDTO", gameDTO);
        return "getGame";
    }

    @GetMapping("/getAllGames")
    public String getaAllGames(Model model) {
        List<GameDTO> gameList = gameService.getAllGames();
        model.addAttribute("gameList", gameList);
        return "getAllGames";
    }

    @GetMapping("/games/search")
    public String searchGamesByGenre(@RequestParam("genre") String genre, Model model) {
        List<GameDTO> gameList = gameService.getGameByGenre(genre);
        model.addAttribute("gameList", gameList);
        return "getAllGames";
    }

    @GetMapping("/newGame")
    public String addGame(Model model) {
        return "addGame";
    }

    @GetMapping("/updateGame/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        GameDTO gameDTO = gameService.getGameById(id);

        String name = gameDTO.getName();
        String description = gameDTO.getDescription();
        String genre = gameDTO.getGenre();

        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("description", description);
        model.addAttribute("genre", genre);
        return "updateGame";
    }
}
